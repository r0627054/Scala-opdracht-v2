package sless.ast.base.components.declaration

import sless.ast.base.components.selector.SelectorComponent
import sless.ast.base.components.value.ValueComponent
import sless.ast.base.components.{PropertyComponent, RuleOrDeclarationComponent}
import sless.ast.base.enumeration.MarginType

/**
  * This class represents a CSS Declaration. A CSS declaration contains a property and a value.
  * It is a subclass of the RuleOrDeclarationComponent used for the nested style rules extension.
  * @param prop   The property of the declaration.
  * @param value  The value of the declaration.
  */
class DeclarationComponent(val prop: PropertyComponent, val value: ValueComponent) extends RuleOrDeclarationComponent {
  override def basic(): String = {
    prop.basic() + ":" + value.basic() + ";"
  }

  override def pretty(spaces: Int): String = {
    prop.pretty(spaces) + ": " + value.pretty(spaces) + ";"
  }

  def containsMarginProperty() : Boolean = {
    prop.containsMarginProperty()
  }

  def getMarginPosition: MarginType.Value = {
    prop.getMarginPosition()
  }

  def getStringValue: String = {
    value.getStringValue()
  }

  def hasPropertyName(name: String) : Boolean = {
    prop.equalsPropertyName(name)
  }

  def getPropertyName: String = prop.getPropertyName()

  override def toBasicComponents(currentParentSelector: SelectorComponent): Seq[RuleOrDeclarationComponent] = Seq(this)
}
