package sless.ast.base.components.declaration

import sless.ast.base.components.{BaseComponent, CssComponent, PropertyComponent, ValueComponent}
import sless.ast.base.enumeration.MarginType


class DeclarationComponent(val prop: PropertyComponent, val value: ValueComponent) extends BaseComponent {
  override def compile(sheet: CssComponent): String = {
    prop.compile(sheet) + ":" + value.compile(sheet) + ";"
  }

  override def pretty(sheet: CssComponent, spaces: Int): String = {
    prop.pretty(sheet,spaces) + ": " + value.pretty(sheet,spaces) + ";"
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
