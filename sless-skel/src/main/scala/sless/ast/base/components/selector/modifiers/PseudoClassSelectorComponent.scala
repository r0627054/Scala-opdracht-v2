package sless.ast.base.components.selector.modifiers

import sless.ast.base.components.selector.SelectorComponent

/**
  * The Pseudo class selector is a type of modifier selector.
  *
  * @param selector The actual selector.
  * @param string   The pseudo class name.
  */
case class PseudoClassSelectorComponent(override val selector: SelectorComponent, override val string: String)
  extends ModifierComponent(selector, string) {

  /**
    * Prints the basic representation of the super class followed by one colon and the pseudo class string.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + pseudoClassSelectorToString()

  /**
    * Prints the pretty representation of the super class followed by one colon and the pseudo class string.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = super.pretty(spaces) + pseudoClassSelectorToString()

  /**
    * The pseudo class syntax is given as a string.
    *
    * @return one colon and the pseudo class string
    */
  def pseudoClassSelectorToString(): String = ":" + string

  /**
    * The pseudo class selector component is returned with the parent selector replaced.
    *
    * @param parentSelector The selector which will replace the parent selector.
    * @return The Selector in which the parent selector is replaced with the given selector.
    */
  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = {
    PseudoClassSelectorComponent(selector.replaceParentWithSelectorComponent(parentSelector), string)
  }
}
