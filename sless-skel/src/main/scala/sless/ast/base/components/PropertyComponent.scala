package sless.ast.base.components

class PropertyComponent(val propertyName: String) extends BaseComponent {
  override def compile(sheet: CssComponent): String = ???

  override def pretty(sheet: CssComponent, spaces: Int): String = ???
}
