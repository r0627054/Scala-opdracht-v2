package sless.ast.base.components.selectors.modifiers
import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

class PseudoElementSelectorComponent(override val selector: SelectorComponent, override val string: String)
  extends ModifierComponent(selector,string){
  override def compile(sheet: CssComponent): String = super.compile(sheet) + pseudoElementSelectorToString()

  override def pretty(sheet: CssComponent, spaces: Int): String = super.pretty(sheet,spaces) + pseudoElementSelectorToString()

  def pseudoElementSelectorToString() : String = "::" + string
}
