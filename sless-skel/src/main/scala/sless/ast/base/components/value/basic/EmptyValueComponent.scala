package sless.ast.base.components.value.basic

import sless.ast.base.components.value.ValueComponent

/**
  * This value component represents an empty value.
  */
class EmptyValueComponent extends ValueComponent{
  /**
    * The string representation of the value equals the empty string.
    * @return The string representation of the value.
    */
  override def getStringValue: String = ""
}
