package sless.ast.base.components

class CssComponent(val rules: Seq[RuleComponent]) extends BaseComponent {
  override def compile(sheet: CssComponent): String = ???

  override def pretty(sheet: CssComponent, spaces: Int): String = ???
}
