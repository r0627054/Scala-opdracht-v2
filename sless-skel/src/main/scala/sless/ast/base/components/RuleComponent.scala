package sless.ast.base.components

import sless.ast.base.components.selectors.SelectorComponent


class RuleComponent(val s: SelectorComponent, declarations: Seq[DeclarationComponent]) extends BaseComponent {
  override def compile(sheet: CssComponent): String = {
    var result: String = s.compile(sheet) + "{"
    declarations.foreach(declaration => result += declaration.compile(sheet) )
    result + "}"
  }

  override def pretty(sheet: CssComponent, spaces: Int): String = {
    var result: String = s.pretty(sheet,spaces) + " {\n"
    declarations.foreach(declaration => result += " "*spaces + declaration.pretty(sheet,spaces) + "\n" )
    result + "}"
  }
}
