package sless.ast.base.components.selector.modifiers

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

class ClassNameSelectorComponent(override val selector: SelectorComponent, override val string: String)
  extends ModifierComponent(selector,string){
  override def basic(): String = super.basic() + "." + string

  override def pretty(spaces: Int): String = super.pretty(spaces) + "." + string

}
