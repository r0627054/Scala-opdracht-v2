package sless.ast.base.components.selectors.modifiers

import sless.ast.base.components.{CssComponent, ValueComponent}
import sless.ast.base.components.selectors.SelectorComponent

class AttributeSelectorComponent(override val selector: SelectorComponent, override val string: String, val value: ValueComponent)
  extends ModifierComponent(selector,string){
  override def basic(): String = super.basic() + "[" + string + "=\"" + value.basic() + "\"]"

  override def pretty(spaces: Int): String = super.pretty(spaces) + "[" + string + "=\"" + value.pretty(spaces) + "\"]"
}
