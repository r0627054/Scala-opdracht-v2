package sless.ast.base.components

import sless.ast.base.components.selectors.SelectorComponent


class RuleComponent(val s: SelectorComponent, declarations: Seq[DeclarationComponent]) extends BaseComponent {
  override def compile(sheet: CssComponent): String = ???

  override def pretty(sheet: CssComponent, spaces: Int): String = ???
}
