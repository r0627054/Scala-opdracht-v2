package sless.ast.base.components.selectors.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

class AllSelectorComponent extends SelectorComponent {
  val allStringSelectorSymbol : String = "*"

  override def compile(sheet: CssComponent): String = allStringSelectorSymbol

  override def pretty(sheet: CssComponent, spaces: Int): String = allStringSelectorSymbol
}
