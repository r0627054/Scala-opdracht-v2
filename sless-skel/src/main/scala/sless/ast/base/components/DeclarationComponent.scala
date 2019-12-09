package sless.ast.base.components


class DeclarationComponent(val prop: PropertyComponent, val value: ValueComponent) extends BaseComponent {
  override def compile(sheet: CssComponent): String = {
    prop.compile(sheet) + ":" + value.compile(sheet) + ";"
  }

  override def pretty(sheet: CssComponent, spaces: Int): String = {
    prop.pretty(sheet,spaces) + ": " + value.pretty(sheet,spaces) + ";"
  }
}
