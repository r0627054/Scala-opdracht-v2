package sless.ast.base.components.selector.modifiers

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

/**
  * The modifier component always consists of a selector and a pseudo element name.
  *
  * @param selector The actual selector.
  * @param string   The element name.
  */
abstract class ModifierComponent(val selector: SelectorComponent, val string: String) extends SelectorComponent {

  /**
    * Prints the selector basic.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = selector.basic()

  /**
    * Prints the selector pretty.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component.
    */
  override def pretty(spaces: Int): String = selector.pretty(spaces)

  /**
    * Extend the selector of the modifier class.
    *
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = selector.extendSelectorReplacement(css)

  /**
    * Checks whether the selector has a parentSelectorComponent
    *
    * @return True when the selector contains the parents selector.
    */
  override def hasParentSelectorComponent: Boolean = selector.hasParentSelectorComponent

}
