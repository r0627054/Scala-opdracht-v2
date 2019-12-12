package sless.ast.base.components.selector.modifiers

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent
import sless.ast.base.components.value.ValueComponent

class AttributeSelectorComponent(override val selector: SelectorComponent, override val string: String, val value: ValueComponent)
  extends ModifierComponent(selector,string){
  override def basic(): String = super.basic() + "[" + string + "=\"" + value.basic() + "\"]"

  override def pretty(spaces: Int): String = super.pretty(spaces) + "[" + string + "=\"" + value.pretty(spaces) + "\"]"

}
