package sless.ast.base

import sless.ast.base.components.selector.SelectorComponent
import sless.ast.base.components._
import sless.ast.base.components.declaration.DeclarationComponent
import sless.ast.base.components.rule.RuleComponent
import sless.ast.base.components.value.ValueComponent
import sless.dsl.BaseDSL

/**
  * The Base AST trait extends the BaseDSL.
  * It overrides all the type declarations an gives them the right value of my implementation.
  * It also overrides the fromRules which creates a Css Component out of the given rules.
  */
trait BaseAST extends BaseDSL {
  override type Rule = RuleComponent
  override type Css = CssComponent
  override type Selector = SelectorComponent
  override type Declaration = DeclarationComponent
  override type Property = PropertyComponent
  override type Value = ValueComponent

  override protected def fromRules(rules: Seq[Rule]): Css = new CssComponent(rules)
}
