package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.RuleOrDeclarationComponent
import sless.ast.base.components.rule.RuleComponent
import sless.ast.base.components.selector.ParentSelectorComponent
import sless.dsl.NestedSelectorDSL

/**
  * A nested selector creator is a creator class which implements the NestedSelectorDSL methods and abstract type members.
  */
trait NestedSelectorCreator extends BaseAST with NestedSelectorDSL {
  override type RuleOrDeclaration = RuleOrDeclarationComponent
  override val Parent: Selector = ParentSelectorComponent()

  protected def bindWithNesting(s: Selector, rules: Seq[RuleOrDeclaration]): Rule = new RuleComponent(s, rules)
}
