package sless.ast.base.components

class CssComponent(val rules: Seq[RuleComponent]) extends BaseComponent {


  def aggregateMargins(): (Boolean, CssComponent) = {
    val (isAggregated,allRules) :(Seq[Boolean], Seq[RuleComponent])=rules.map(rule => rule.aggregateMargins()).unzip
    (isAggregated.forall(isAgg => isAgg), new CssComponent(allRules))
  }

  override def compile(sheet: CssComponent): String = {
    var result : String = ""
    rules.foreach(r => result +=  r.compile(sheet))
    result
  }

  override def pretty(sheet: CssComponent, spaces: Int): String = {
    var result : String = ""
    val lastIndex : Int = if(rules.nonEmpty) rules.length - 1 else 0
    for((rule,index) <- rules.view.zipWithIndex) {
      result += rule.pretty(sheet,spaces)
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

}
