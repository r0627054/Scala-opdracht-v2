package sless.ast.base.components.selector.modifiers


import sless.ast.base.components.selector.SelectorComponent
import sless.ast.base.components.value.ValueComponent

case class AttributeSelectorComponent(override val selector: SelectorComponent, override val string: String, value: ValueComponent)
  extends ModifierComponent(selector,string){

  /**
    * Prints the basic representation of the attribute string and value of the attribute. Both between brackets.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + "[" + string + "=\"" + value.basic() + "\"]"

  /**
    * Prints the pretty representation of the attribute string and value of the attribute. Both between brackets.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = super.pretty(spaces) + "[" + string + "=\"" + value.pretty(spaces) + "\"]"


  /**
    * The attribute selector component is returned with the parent selector replaced.
    *
    * @param parentSelector The selector which will replace the parent selector.
    * @return The Selector in which the parent selector is replaced with the given selector.
    */
  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = {
     AttributeSelectorComponent(selector.replaceParentWithSelectorComponent(parentSelector),string,value)
  }
}
