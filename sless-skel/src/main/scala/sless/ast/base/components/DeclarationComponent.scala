package sless.ast.base.components


class DeclarationComponent(val prop: PropertyComponent, val value: ValueComponent) extends BaseComponent {
  override def compile(sheet: CssComponent): String = ???

  override def pretty(sheet: CssComponent, spaces: Int): String = ???
}
