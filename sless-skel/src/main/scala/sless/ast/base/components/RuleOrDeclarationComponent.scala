package sless.ast.base.components

import sless.ast.base.components.declaration.DeclarationComponent
import sless.ast.base.components.rule.RuleComponent
import sless.ast.base.components.selector.SelectorComponent

abstract class RuleOrDeclarationComponent extends BaseComponent {
  def containsMarginProperty(): Boolean

  def toBasicComponents(currentParentSelector: SelectorComponent):  Seq[RuleOrDeclarationComponent]
  def isRuleComponent() : Boolean = this.isInstanceOf[RuleComponent]
  def isDeclarationComponent() : Boolean = this.isInstanceOf[DeclarationComponent]
}
