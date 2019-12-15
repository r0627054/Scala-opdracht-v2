package sless.ast.base.components.selector.modifiers

import sless.ast.base.components.selector.SelectorComponent

/**
  * The class name selector is a type of modifier selector.
  *
  * @param selector The actual selector.
  * @param string   The  class name.
  */
case class ClassNameSelectorComponent(override val selector: SelectorComponent, override val string: String)
  extends ModifierComponent(selector, string) {

  /**
    * Prints the basic representation of the super class followed by a point and the class name.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + "." + string

  /**
    * Prints the pretty representation of the super class followed by a point and the class name.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = super.pretty(spaces) + "." + string

  /**
    * The class selector component is returned with the parent selector replaced.
    *
    * @param parentSelector The selector which will replace the parent selector.
    * @return The Selector in which the parent selector is replaced with the given selector.
    */
  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = {
    ClassNameSelectorComponent(selector.replaceParentWithSelectorComponent(parentSelector), string)
  }
}
