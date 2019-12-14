package sless.ast.base.components.selector.combinators

import sless.ast.base.components.selector.SelectorComponent

/**
  * The descendant selector component represents the descendant CSS selector.
  * The descendant selector matches all elements that are descendants of a specified element.
  *
  * @param firstSelector  The first (left) selector component.
  * @param secondSelector The second (right) selector component.
  */
case class DescendantSelectorComponent(override val firstSelector: SelectorComponent, override val secondSelector: SelectorComponent)
  extends CombinatorSelectorComponent(firstSelector, secondSelector) {

  /**
    * Prints both selectors basic with " " in between.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = toStringCompileWithMiddleCharacter(" ")

  /**
    * Prints both selectors pretty with " " in between.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = toStringPrettyMiddleCharacterWithoutExtraSpaces(" ", spaces)

  /**
    * Replaces the parent selector in both the first or the second selector.
    *
    * @param parentSelector The selector which will replace the parent selector.
    * @return The Selector in which the parent selector is replaced with the given selector.
    */
  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = {
    DescendantSelectorComponent(firstSelector.replaceParentWithSelectorComponent(parentSelector), secondSelector.replaceParentWithSelectorComponent(parentSelector))
  }
}
