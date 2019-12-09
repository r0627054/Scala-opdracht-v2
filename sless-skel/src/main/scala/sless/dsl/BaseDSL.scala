package sless.dsl

/**
  * This traits allows me to specify my implementation of the types Rule, Css, Selector, Declaration, Property, Value
  */
trait BaseDSL {
  type Rule
  type Css
  type Selector
  type Declaration
  type Property
  type Value

  def css(rules: Rule*): Css = fromRules(rules.toSeq)

  protected def fromRules(rules: Seq[Rule]): Css
}


