package sless.ast.base.components.value.basic

import sless.ast.base.components.value.ValueComponent

/**
  * This value component represents the "auto" value.
  */
class AutoValueComponent extends ValueComponent {
  /**
    * The string representation of the value equals "auto"
    *
    * @return The string representation of the value.
    */
  override def getStringValue: String = "auto"
}
