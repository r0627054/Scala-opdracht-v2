package sless.ast.base.components

class ValueComponent(val value: String) extends BaseComponent {
  override def basic(): String = value

  override def pretty(spaces: Int): String = value
}
