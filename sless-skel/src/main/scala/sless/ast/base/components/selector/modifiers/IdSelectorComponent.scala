package sless.ast.base.components.selector.modifiers

import sless.ast.base.components.selector.SelectorComponent

/**
  * The Id selector is a type of modifier selector.
  *
  * @param selector The actual selector.
  * @param string   The id element name.
  */
case class IdSelectorComponent(override val selector: SelectorComponent, override val string: String)
  extends ModifierComponent(selector, string) {

  /**
    * Prints the basic representation of the super class followed by a hashtag and the id string.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + toStringIdSelector

  /**
    * Prints the pretty representation of the super class followed by a hashtag and the id string.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = super.pretty(spaces) + toStringIdSelector

  /**
    * The id selector name as a string
    *
    * @return hashtag followed by the name of the id.
    */
  def toStringIdSelector: String = "#" + string

  /**
    * The Id selector component is returned with the parent selector replaced.
    *
    * @param parentSelector The selector which will replace the parent selector.
    * @return The Selector in which the parent selector is replaced with the given selector.
    */
  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = {
    IdSelectorComponent(selector.replaceParentWithSelectorComponent(parentSelector), string)
  }
}
