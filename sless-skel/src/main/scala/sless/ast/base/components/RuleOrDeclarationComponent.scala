package sless.ast.base.components

import sless.ast.base.components.declaration.DeclarationComponent
import sless.ast.base.components.rule.RuleComponent
import sless.ast.base.components.selector.SelectorComponent

/**
  * The rule or declaration component is an abstract class. It sub classes are either a DeclarationComponent or a
  * RuleComponent. The class is needed for the nested style rules implementation. This is because a rule can consist of
  * a list of both actual rules or declarations.
  */
abstract class RuleOrDeclarationComponent extends BaseComponent {
  /**
    * Checks whether the rule or declaration contains a margin property or not.
    *
    * @return true when the component contains a margin property; otherwise false.
    */
  def containsMarginProperty(): Boolean

  /**
    * This method is used to resolved all RuleOrDeclarationComponent instances to basic RuleComponents
    * or DeclarationComponent instances. Also the parent selector is resolved.
    *
    * @param currentParentSelector the currentParentSelector of the component.
    * @return The Seq of basic RuleComponents with their basic DeclarationComponents.
    */
  def toBasicComponents(currentParentSelector: SelectorComponent): Seq[RuleOrDeclarationComponent]

  /**
    * Checks whether this component is a RuleComponent.
    *
    * @return true when this is a RuleComponent; otherwise false.
    */
  def isRuleComponent: Boolean = this.isInstanceOf[RuleComponent]

  /**
    * Checks whether this component is a DeclarationComponent.
    *
    * @return true when this is a DeclarationComponent; otherwise false.
    */
  def isDeclarationComponent: Boolean = this.isInstanceOf[DeclarationComponent]
}
