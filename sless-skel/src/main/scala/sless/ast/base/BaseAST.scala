package sless.ast.base

import sless.ast.base.components.selectors.SelectorComponent
import sless.ast.base.components._
import sless.dsl.BaseDSL

trait BaseAST extends BaseDSL  {
  override type Rule = RuleComponent
  override type Css = CssComponent
  override type Selector = SelectorComponent
  override type Declaration = DeclarationComponent
  override type Property = PropertyComponent
  override type Value = ValueComponent
  override protected def fromRules(rules: Seq[Rule]): Css = new CssComponent(rules)
}
