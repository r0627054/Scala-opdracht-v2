package sless.ast.base.components.selector.modifiers

import sless.ast.base.components.selector.SelectorComponent

/**
  * The Pseudo element selector is a type of modifier selector.
  *
  * @param selector The actual selector.
  * @param string   The pseudo element name.
  */
case class PseudoElementSelectorComponent(override val selector: SelectorComponent, override val string: String)
  extends ModifierComponent(selector, string) {

  /**
    * Prints the basic representation of the super class followed by two colons and the pseudo element string.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + pseudoElementSelectorToString()

  /**
    * Prints the pretty representation of the super class followed by two colons and the pseudo element string.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = super.pretty(spaces) + pseudoElementSelectorToString()

  /**
    * The pseudo element syntax is given as a string.
    *
    * @return Two colons and the pseudo element string
    */
  def pseudoElementSelectorToString(): String = "::" + string

  /**
    * The pseudo element selector component is returned with the parent selector replaced.
    *
    * @param parentSelector The selector which will replace the parent selector.
    * @return The Selector in which the parent selector is replaced with the given selector.
    */
  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = {
    PseudoElementSelectorComponent(selector.replaceParentWithSelectorComponent(parentSelector), string)
  }
}
