package sless.ast.base.components.selector.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

class AllSelectorComponent extends SelectorComponent {
  val allStringSelectorSymbol : String = "*"

  override def basic(): String = allStringSelectorSymbol

  override def pretty(spaces: Int): String = allStringSelectorSymbol
}
