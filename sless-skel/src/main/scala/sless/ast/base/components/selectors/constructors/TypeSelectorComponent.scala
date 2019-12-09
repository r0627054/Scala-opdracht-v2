package sless.ast.base.components.selectors.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

class TypeSelectorComponent(val string:String) extends SelectorComponent{
  override def compile(sheet: CssComponent): String = string

  override def pretty(sheet: CssComponent, spaces: Int): String = ???
}
