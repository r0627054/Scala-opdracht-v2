package sless.ast.base.components.value.basic

import sless.ast.base.components.value.ValueComponent

class EmptyValueComponent extends ValueComponent{
  override def getStringValue(): String = ""
}
