package sless.ast.base.components.value.basic

import sless.ast.base.components.value.ValueComponent

/**
  * This value component represents a basic value. This value is a represented as a string.
  *
  * @param value The value of the component represented by a string.
  */
class BasicValueComponent(val value: String) extends ValueComponent {
  /**
    * The string representation of the value.
    *
    * @return The string representation of the value.
    */
  override def getStringValue: String = value
}
