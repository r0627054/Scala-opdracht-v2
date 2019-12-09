package sless.dsl

import sless.ast.base.{CssAST, DeclarationAST, PropertyAST, RuleAST, SelectorAST, ValueAST}

/**
  * This traits allows me to specify my implementation of the types Rule, Css, Selector, Declaration, Property, Value
  */
trait BaseDSL {
  type Rule = RuleAST
  //type for entire style sheet
  type Css = CssAST
  type Selector = SelectorAST
  type Declaration = DeclarationAST
  type Property = PropertyAST
  type Value = ValueAST

  //the method allows us to construct a CSS sheet
  //it uses the internal method "fromRules"
  def css(rules: Rule*): Css = fromRules(rules.toSeq)

  //creates a instance of the css class with given rules
  protected def fromRules(rules: Seq[Rule]): Css = new CssAST(rules)
}


