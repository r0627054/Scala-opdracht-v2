package sless.ast.base.components

class PropertyComponent(val propertyName: String) extends BaseComponent {
  override def compile(sheet: CssComponent): String = propertyName

  override def pretty(sheet: CssComponent, spaces: Int): String = propertyName
}
