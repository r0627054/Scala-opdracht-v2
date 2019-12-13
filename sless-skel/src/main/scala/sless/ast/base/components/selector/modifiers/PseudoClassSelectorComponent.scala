package sless.ast.base.components.selector.modifiers

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent


case class PseudoClassSelectorComponent(override val selector: SelectorComponent, override val string: String)
  extends ModifierComponent(selector,string){
  override def basic(): String = super.basic() + pseudoClassSelectorToString()

  override def pretty(spaces: Int): String = super.pretty(spaces) + pseudoClassSelectorToString()

  def pseudoClassSelectorToString() : String = ":" + string

  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = {
    new PseudoClassSelectorComponent(selector.replaceParentWithSelectorComponent(parentSelector),string)
  }
}
