package sless.ast.base.components.selector.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

/**
  * The type selector component represents a selector for a type.
  * @param string the string representation of the type.
  */
case class TypeSelectorComponent(string: String) extends SelectorComponent {

  /**
    * Prints the name of the type.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = string

  /**
    * Prints the name of the type.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = string

  /**
    * In case of a type selector the input css in just returned.
    * No replacements has to be made.
    *
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = css

  /**
    * Returns this selector, the type selector cannot contain a parent selector.
    *
    * @param parentSelector The selector which will replace the parent selector.
    * @return The Selector in which the parent selector is replaced with the given selector.
    */
  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = this

  /**
    * Returns false because this type selector cannot contain a parent.
    * @return True when the selector contains the parents selector.
    */
  override def hasParentSelectorComponent: Boolean = false
}
