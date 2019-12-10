package sless.ast.base.components

import javafx.print.Printer.MarginType

class PropertyComponent(val propertyName: String) extends BaseComponent {
  override def compile(sheet: CssComponent): String = propertyName

  override def pretty(sheet: CssComponent, spaces: Int): String = propertyName

  def containsMarginProperty() : Boolean = {
    propertyName.startsWith("margin-")
  }

  def getMarginPosition() : MarginType = {
    if(!this.containsMarginProperty()){
      throw new IllegalArgumentException("This is not a margin property!")
    }
    MarginType.valueOf(propertyName.substring(7))
  }

}
