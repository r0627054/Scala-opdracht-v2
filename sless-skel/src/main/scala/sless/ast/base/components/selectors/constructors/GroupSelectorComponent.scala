package sless.ast.base.components.selectors.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

class GroupSelectorComponent(val selectors: Seq[SelectorComponent]) extends SelectorComponent{
  override def compile(sheet: CssComponent): String = ???

  override def pretty(sheet: CssComponent, spaces: Int): String = ???
}
