package sless.ast.base.components.selector.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

class TypeSelectorComponent(val string:String) extends SelectorComponent{
  override def basic(): String = string

  override def pretty(spaces: Int): String = string
}
