package sless.ast.base.components.value.basic

import sless.ast.base.components.value.ValueComponent

/**
  * This value component represents the "inherit" value.
  */
class InheritValueComponent extends ValueComponent {
  /**
    * The string representation of the value equals "inherit"
    * @return The string representation of the value.
    */
  override def getStringValue: String = "inherit"
}
