package sless.ast.base.components

import sless.ast.base.components.rule.RuleComponent
import sless.ast.base.components.selector.SelectorComponent
import sless.ast.base.components.selector.constructors.AllSelectorComponent

/**
  * The CSS component represents the actual CSS sheet. It is made out of a Seq of RuleComponents.
  *
  * @param rules The sequence of rules.
  */
class CssComponent(val rules: Seq[RuleComponent]) extends BaseComponent {

  /**
    * Aggregates all the margin declarations to one if all margin declarations are available.
    *
    * @return A tuple whether or not the all aggregate margins are available and the newly created CSS component.
    */
  def aggregateMargins(): (Boolean, CssComponent) = {
    val (isAggregated, allRules): (Seq[Boolean], Seq[RuleComponent]) = getBasicRulesOfCss.map(rule => rule.aggregateMargins()).unzip
    (isAggregated.forall(isAgg => isAgg), new CssComponent(allRules))
  }

  /**
    * The css component is resolved to basic CSS components. All the basic components are printed as a basic string.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = {
    var result: String = ""
    replaceAllExtendedSelectors().rules.foreach(r => result += r.basic())
    result
  }

  /**
    * The css component is resolved to basic CSS components. All the basic components are printed as a pretty string.
    *
    * @param spaces The number of spaces places before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = {
    var result: String = ""
    val currentRules = replaceAllExtendedSelectors().rules
    val lastIndex: Int = if (currentRules.nonEmpty) currentRules.length - 1 else 0
    for ((rule, index) <- currentRules.view.zipWithIndex) {
      result += rule.pretty(spaces)
      if (index != lastIndex) result += "\n\n"
    }
    result
  }

  /**
    * Removes all the empty rules.
    *
    * @return All the rule components which are not empty.
    */
  def removedEmptyRules: Seq[RuleComponent] = {
    getBasicRulesOfCss.filter(r => r.hasDeclarations)
  }

  /**
    * Checks whether the CSS component contains rules which are empty.
    *
    * @return True when an empty rule exists; otherwise false.
    */
  def hasAnEmptyRule: Boolean = {
    rules.exists(r => r.hasNoDeclarations)
  }

  /**
    * Removes the empty rules and checks if there were any.
    *
    * @return A tuple: the first element checks whether there were any empty rules, the second element is the css
    *         component where the empty rules are deleted.
    */
  def removeEmptyRules(): (Boolean, CssComponent) = {
    (hasAnEmptyRule, new CssComponent(removedEmptyRules))
  }

  /**
    * Returns how many declarations have a property with the given name.
    *
    * @param propertyName The name of a property
    * @return How many declarations have a property with the given name.
    */
  def numberOfDeclarationsOfPropertyWithName(propertyName: String): Int = {
    getBasicRulesOfCss.map(rule => rule.numberOfDeclarationsOfPropertyWithName(propertyName)).sum
  }

  //-------------------------------
  //------- extend methods --------
  //-------------------------------

  /**
    * It replaces the selector of each rule if it old selector equals oldSelector.
    * This is replaced with the newSelector
    *
    * @param oldSelector The old selector which needs to be replaced.
    * @param newSelector The new selector to which it will be set.
    * @return The new Css component where the selector is replaced.
    */
  def replaceGivenSelectorWith(oldSelector: SelectorComponent, newSelector: SelectorComponent): Css = {
    val replacedRules: Seq[Rule] = getBasicRulesOfCss.map(rule => rule.replaceGivenSelectorWith(oldSelector, newSelector))
    new CssComponent(replacedRules)
  }

  /**
    * First the CSS sheets is transformed such that it only contains basic components.
    * In these basic components all the extended selectors are replaced.
    *
    * @return The newly created css Sheet with basic components and extended selectors replaced.
    */
  def replaceAllExtendedSelectors(): CssComponent = {
    val currentRules: Seq[RuleComponent] = getBasicRulesOfCss
    var currentCss: CssComponent = this.toBasicComponents
    for (rule <- currentRules) {
      currentCss = rule.extendSelectorReplacement(currentCss)
    }
    currentCss
  }

  //-------------------------------
  //-------- merge methods --------
  //-------------------------------

  /**
    * Merges all the given CSS sheets into one sheet.
    *
    * @param sheets The CSS sheets which need to be merged.
    * @return One merged sheet.
    */
  def mergeSheets(sheets: Seq[CssComponent]): CssComponent = {
    if (sheets.isEmpty) {
      //if no other sheets need to be merged
      this
    } else {
      //all the extended selectors are replaced
      var currentMergedSheet: CssComponent = this.replaceAllExtendedSelectors()
      for (sheet <- sheets) {
        currentMergedSheet = mergeCssComponents(currentMergedSheet, sheet.replaceAllExtendedSelectors())
      }
      currentMergedSheet
    }
  }

  /**
    * This method will merge two sheets into one. Same selectors will be placed together.
    *
    * @param firstSheet  The first CSS sheet which will be merged.
    * @param secondSheet The second CSS sheet which will be merged.
    * @return The result of merging both CSS sheets.
    */
  def mergeCssComponents(firstSheet: CssComponent, secondSheet: CssComponent): CssComponent = {
    var newLeftRules: Seq[RuleComponent] = Seq()
    for (firstSheetRule <- firstSheet.rules) {
      newLeftRules = newLeftRules ++ firstSheetRule.mergeInSheet(secondSheet)
    }
    plainMerge(new CssComponent(newLeftRules), secondSheet)
  }

  /**
    * A plain merge will append both sheets into one CSS sheet, without checking similar selectors.
    *
    * @param firstSheet  The first CSS sheet which will be merged.
    * @param secondSheet The second CSS sheet which will be merged.
    * @return The result of plain merging both CSS sheets.
    */
  def plainMerge(firstSheet: CssComponent, secondSheet: CssComponent): CssComponent = {
    new CssComponent(firstSheet.rules ++ secondSheet.rules)
  }

  /**
    * Checks whether there is a rule which has the same selector as the given selector.
    *
    * @param sel The selector which will be checked with all the selectors in the sheet.
    * @return True when the selector occurs in the sheets; otherwise false.
    */
  def hasSameSelector(sel: Selector): Boolean = {
    rules.exists(rule => rule.hasSameSelector(sel))
  }

  /**
    * Returns the rule which has the given selector.
    *
    * @throws IllegalArgumentException when there does not exist a rule with the given selector.
    * @param sel the selector the rule needs to contain.
    * @return The rule which contains the given selector.
    */
  def getRuleOfSelector(sel: Selector): RuleComponent = {
    var hasResult: Boolean = false
    var ruleResult: RuleComponent = new RuleComponent(AllSelectorComponent(), Seq())
    for (rule <- rules) {
      if (rule.hasSameSelector(sel)) {
        hasResult = true
        ruleResult = rule
      }
    }
    if (!hasResult) {
      throw new IllegalArgumentException("Only returns a rule when the selector exists")
    } else {
      ruleResult
    }
  }

  //-------------------------
  //-------NESTED -----------
  //-------------------------


  /**
    * This function will give a new CSS component which only consist of basic components.
    * Basic components are non nested components.
    *
    * @return The new basic CSS component.
    */
  def toBasicComponents: CssComponent = {
    val rulesMadeOfBasicComponents: Seq[RuleComponent] = rules.flatMap(rule => rule.toBasicComponents())
    new CssComponent(rulesMadeOfBasicComponents)
  }

  /**
    * This function will give a sequence of all the basic rule components. These rule components don't have nested rules.
    *
    * @return Sequence of non nested (=basic) rule components.
    */
  def getBasicRulesOfCss: Seq[RuleComponent] = {
    this.toBasicComponents.rules
  }
}
