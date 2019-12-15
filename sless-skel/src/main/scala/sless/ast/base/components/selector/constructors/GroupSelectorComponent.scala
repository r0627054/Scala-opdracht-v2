package sless.ast.base.components.selector.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

/**
  * A group selector represents a group of different selectors.
  *
  * @param selectors the Seq (group) of selectors.
  */
case class GroupSelectorComponent(selectors: Seq[SelectorComponent]) extends SelectorComponent {

  /**
    * Prints all the selectors basic separated with a comma.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = {
    var result: String = ""
    val lastIndex: Int = if (selectors.nonEmpty) selectors.length - 1 else 0
    for ((selector, index) <- selectors.view.zipWithIndex) {
      result += selector.basic()
      if (index != lastIndex) result += ","
    }
    result
  }

  /**
    * Prints all the selectors pretty separated with a comma.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = {
    var result: String = ""
    val lastIndex: Int = if (selectors.nonEmpty) selectors.length - 1 else 0
    for ((selector, index) <- selectors.view.zipWithIndex) {
      result += selector.pretty(spaces)
      if (index != lastIndex) result += ", "
    }
    result
  }

  /**
    * On a group of selectors each selector needs to be extended starting form the far most selector, ending at the far
    * most left selector.
    *
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = {
    var currentCss: CssComponent = css
    for (selectorComponent <- selectors.reverse) {
      currentCss = selectorComponent.extendSelectorReplacement(currentCss)
    }
    currentCss
  }

  /**
    * Replaces all the parents selectors in the group.
    *
    * @param parentSelector The selector which will replace the parent selector.
    * @return The Selector in which the parent selector is replaced with the given selector.
    */
  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = {
    GroupSelectorComponent(selectors.map(selector => selector.replaceParentWithSelectorComponent(parentSelector)))
  }

  /**
    * Checks whether one or more selectors is a parent selector.
    *
    * @return True when the selector contains the parents selector.
    */
  override def hasParentSelectorComponent: Boolean = selectors.exists(sel => sel.hasParentSelectorComponent)
}
