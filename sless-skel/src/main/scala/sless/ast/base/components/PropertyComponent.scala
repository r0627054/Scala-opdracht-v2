package sless.ast.base.components

import sless.ast.base.enumeration.MarginType


class PropertyComponent(val propertyName: String) extends BaseComponent {
  override def compile(sheet: CssComponent): String = propertyName

  override def pretty(sheet: CssComponent, spaces: Int): String = propertyName

  def containsMarginProperty() : Boolean = {
    propertyName.startsWith("margin-")
  }

  def getMarginPosition() : MarginType.Value = {
    if(!this.containsMarginProperty()){
      throw new IllegalArgumentException("This is not a margin property!")
    }
    MarginType.withName(propertyName.substring(7))
  }

}
