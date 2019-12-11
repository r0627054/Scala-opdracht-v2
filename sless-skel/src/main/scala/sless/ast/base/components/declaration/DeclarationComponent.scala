package sless.ast.base.components.declaration

import sless.ast.base.components.{BaseComponent, CssComponent, PropertyComponent, ValueComponent}
import sless.ast.base.enumeration.MarginType


class DeclarationComponent(val prop: PropertyComponent, val value: ValueComponent) extends BaseComponent {
  override def basic(): String = {
    prop.basic() + ":" + value.basic() + ";"
  }

  override def pretty(spaces: Int): String = {
    prop.pretty(spaces) + ": " + value.pretty(spaces) + ";"
  }

  def containsMarginProperty() : Boolean = {
    prop.containsMarginProperty()
  }

  def getMarginPosition() : MarginType.Value = {
    prop.getMarginPosition()
  }

  def getStringValue() : String = {
    value.value
  }

  def hasPropertyName(name: String) : Boolean = {
    prop.equalsPropertyName(name)
  }

}
