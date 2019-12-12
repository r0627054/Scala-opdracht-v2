package sless.ast.base.components.value.basic

import sless.ast.base.components.value.ValueComponent

class BasicValueComponent(val value: String) extends ValueComponent {
  override def getStringValue(): String = value
}
