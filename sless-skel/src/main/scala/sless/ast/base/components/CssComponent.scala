package sless.ast.base.components

import sless.ast.base.components.rule.RuleComponent
import sless.ast.base.components.selector.SelectorComponent
import sless.ast.base.components.selector.constructors.AllSelectorComponent

class CssComponent(val rules: Seq[RuleComponent]) extends BaseComponent {

  def aggregateMargins(): (Boolean, CssComponent) = {
    val (isAggregated,allRules) :(Seq[Boolean], Seq[RuleComponent])=getBasicRulesOfCss.map(rule => rule.aggregateMargins()).unzip
    (isAggregated.forall(isAgg => isAgg), new CssComponent(allRules))
  }

  override def basic(): String = {
    var result : String = ""
    //rules.foreach(r => result +=  r.basic())
    replaceAllExtendedSelectors().rules.foreach(r => result +=  r.basic())
    result
  }

  override def pretty(spaces: Int): String = {
    var result : String = ""
    val currentRules = replaceAllExtendedSelectors().rules
    val lastIndex : Int = if(currentRules.nonEmpty) currentRules.length - 1 else 0
    for((rule,index) <- currentRules.view.zipWithIndex) {
      result += rule.pretty(spaces)
      if(index != lastIndex) result += "\n\n"
    }
    result
  }

  def removedEmptyRules: Seq[RuleComponent] = {
    getBasicRulesOfCss.filter(r => r.hasDeclarations)
  }

  def hasAnEmptyRule: Boolean = {
    getBasicRulesOfCss.exists(r => r.hasNoDeclarations)
  }

  def removeEmptyRules(): (Boolean, CssComponent) = {
    (hasAnEmptyRule,new CssComponent(removedEmptyRules))
  }

  def numberOfDeclarationsOfPropertyWithName(propertyName: String) : Int = {
    getBasicRulesOfCss.map(rule => rule.numberOfDeclarationsOfPropertyWithName(propertyName)).sum
  }

  //-------------------------------
  //------- extend methods --------
  //-------------------------------


  def replaceGivenSelectorWith(oldSelector: SelectorComponent, newSelector: SelectorComponent): Css ={
    val replacedRules: Seq[Rule] = getBasicRulesOfCss.map(rule => rule.replaceGivenSelectorWith(oldSelector,newSelector))
    new CssComponent(replacedRules)
  }

  def replaceAllExtendedSelectors() : CssComponent = {

    //TODO needs to have basic css
    //var currentCss : CssComponent = this
    val currentRules : Seq[RuleComponent] = getBasicRulesOfCss
    var currentCss : CssComponent = this.toBasicComponents()
    for(rule <- currentRules) {
      currentCss = rule.extendSelectorReplacement(currentCss)
    }
    currentCss
  }

  //-------------------------------
  //-------- merge methods --------
  //-------------------------------

  def mergeSheets(tail: Seq[CssComponent]): CssComponent = {
    if(tail.isEmpty){
       this
    } else {
      //all the extended selectors are replaced
      var currentMergedSheet : CssComponent = this.replaceAllExtendedSelectors()
      for(sheet <- tail){
        currentMergedSheet = mergeCssComponents(currentMergedSheet, sheet.replaceAllExtendedSelectors())
      }
      currentMergedSheet
    }
  }

  //selector list are supported
  //nested selectors are not supported
  //als er unieke declaraties zijn bij een propertie zijn zet deze dan bij de rechtse
  //als er dubbele declaraties zijn bij een propertie behoud de rechtse
  def mergeCssComponents(firstSheet: CssComponent, secondSheet: CssComponent): CssComponent = {
    var newLeftRules : Seq[RuleComponent] = Seq()
    for(firstSheetRule <- firstSheet.rules){
      newLeftRules = newLeftRules ++ firstSheetRule.mergeInSheet(secondSheet)
    }
    plainMerge(new CssComponent(newLeftRules),secondSheet)
  }

  def plainMerge(firstSheet: CssComponent, secondSheet: CssComponent): CssComponent = {
    new CssComponent(firstSheet.rules ++ secondSheet.rules)
  }

  def hasSameSelector(sel: Selector) : Boolean = {
    rules.map(rule => rule.hasSameSelector(sel)).contains(true)
  }

  def getRuleOfSelector(sel: Selector) : RuleComponent = {
    var hasResult : Boolean = false
    var ruleResult : RuleComponent = new RuleComponent(new AllSelectorComponent(),Seq())
    for(rule <- rules){
      if(rule.hasSameSelector(sel)){
        hasResult = true
        ruleResult =rule
      }
    }
    if(!hasResult){
      throw new IllegalArgumentException("Only returns a rule when the selector exists")
    } else {
      ruleResult
    }
  }

  //-------------------------
  //-------NESTED -----------
  //-------------------------


  def toBasicComponents() : CssComponent = {
      val rulesMadeOfBasicComponents : Seq[RuleComponent] = rules.map(rule => rule.toBasicComponents()).flatten
      new CssComponent(rulesMadeOfBasicComponents)
  }

  def getBasicRulesOfCss() : Seq[RuleComponent] = {
    this.toBasicComponents().rules
  }


}
