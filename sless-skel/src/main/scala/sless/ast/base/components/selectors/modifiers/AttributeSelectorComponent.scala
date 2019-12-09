package sless.ast.base.components.selectors.modifiers

import sless.ast.base.components.{CssComponent, ValueComponent}
import sless.ast.base.components.selectors.SelectorComponent

class AttributeSelectorComponent(override val selector: SelectorComponent, override val string: String, val value: ValueComponent)
  extends ModifierComponent(selector,string){
  override def compile(sheet: CssComponent): String = super.compile(sheet) + "[" + string + "=\"" + value.compile(sheet) + "\"]"

  override def pretty(sheet: CssComponent, spaces: Int): String = super.pretty(sheet,spaces) + "[" + string + "=\"" + value.pretty(sheet,spaces) + "\"]"
}
