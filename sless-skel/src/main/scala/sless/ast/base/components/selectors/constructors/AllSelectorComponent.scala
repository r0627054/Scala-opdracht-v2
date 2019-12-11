package sless.ast.base.components.selectors.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

class AllSelectorComponent extends SelectorComponent {
  val allStringSelectorSymbol : String = "*"

  override def basic(): String = allStringSelectorSymbol

  override def pretty(spaces: Int): String = allStringSelectorSymbol
}
