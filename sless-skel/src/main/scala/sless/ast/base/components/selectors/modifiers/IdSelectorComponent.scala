package sless.ast.base.components.selectors.modifiers

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

class IdSelectorComponent(override val selector: SelectorComponent, override val string: String)
  extends ModifierComponent(selector,string){
  override def basic(): String = super.basic() + toStringIdSelector()

  override def pretty(spaces: Int): String = super.pretty(spaces) + toStringIdSelector()

  def toStringIdSelector() : String = "#" + string
}
