package sless.ast.base.components

import sless.ast.base.components.rule.RuleComponent
import sless.ast.base.components.selector.SelectorComponent

class CssComponent(val rules: Seq[RuleComponent]) extends BaseComponent {


  def aggregateMargins(): (Boolean, CssComponent) = {
    val (isAggregated,allRules) :(Seq[Boolean], Seq[RuleComponent])=rules.map(rule => rule.aggregateMargins()).unzip
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
    rules.filter(r => r.hasDeclarations)
  }

  def hasAnEmptyRule: Boolean = {
    rules.exists(r => r.hasNoDeclarations)
  }

  def removeEmptyRules(): (Boolean, CssComponent) = {
    (hasAnEmptyRule,new CssComponent(removedEmptyRules))
  }

  def numberOfDeclarationsOfPropertyWithName(propertyName: String) : Int = {
    rules.map(rule => rule.numberOfDeclarationsOfPropertyWithName(propertyName)).sum
  }

  def replaceGivenSelectorWith(oldSelector: SelectorComponent, newSelector: SelectorComponent): Css ={
    val replacedRules: Seq[Rule] = rules.map(rule => rule.replaceGivenSelectorWith(oldSelector,newSelector))
    new CssComponent(replacedRules)
  }

  def replaceAllExtendedSelectors() : CssComponent = {
    var currentCss : CssComponent = this
    for(rule <- rules) {
      currentCss = rule.extendSelectorReplacement(currentCss)
    }
    currentCss
  }

}
