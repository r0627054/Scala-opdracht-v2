package sless.ast.base.components.value

import sless.ast.base.components.BaseComponent

abstract class ValueComponent extends BaseComponent {
  def getStringValue() : String
  override def basic(): String = getStringValue()
  override def pretty(spaces: Int): String = getStringValue()
}
