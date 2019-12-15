package sless.ast.base.components.selector.combinators

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

/**
  * A combinator selector is Selector component composed of two other selector components.
  *
  * @param firstSelector  The first (left) selector component.
  * @param secondSelector The second (right) selector component.
  */
abstract class CombinatorSelectorComponent(val firstSelector: SelectorComponent, val secondSelector: SelectorComponent) extends SelectorComponent {

  /**
    * Given a middle character a basic string is created.
    * It takes the basic representation of the first selector with the middle character and the basic representation of
    * the secondSelector.
    *
    * @param middleCharacter The given middle character between the selectors.
    * @return The basic string representation.
    */
  def toStringCompileWithMiddleCharacter(middleCharacter: String): String = firstSelector.basic() + middleCharacter + secondSelector.basic()

  /**
    * Given a middle character a basic string is created.
    * It takes the pretty representation of the first selector with the middle character, between spaces, and the basic representation of
    * the secondSelector.
    *
    * @param middleCharacter The given middle character between the selectors.
    * @return The pretty string, with spaces, representation.
    */
  def toStringPrettyMiddleCharacterWithExtraSpaces(middleCharacter: String, spaces: Int): String = firstSelector.pretty(spaces) + " " + middleCharacter + " " + secondSelector.pretty(spaces)

  /**
    * Given a middle character a basic string is created.
    * It takes the pretty representation of the first selector with the middle character and the basic representation of
    * the secondSelector.
    *
    * @param middleCharacter The given middle character between the selectors.
    * @return The pretty string representation.
    */
  def toStringPrettyMiddleCharacterWithoutExtraSpaces(middleCharacter: String, spaces: Int): String = firstSelector.pretty(spaces) + middleCharacter + secondSelector.pretty(spaces)

  /**
    * Extend the selector of the combinator classes.
    * First the right component has to be extended. After this, the left component.
    *
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = {
    val rightCss: CssComponent = secondSelector.extendSelectorReplacement(css)
    firstSelector.extendSelectorReplacement(rightCss)
  }

  /**
    * Checks both the first and second selector, if they have any parent selector.
    *
    * @return True when the selector contains the parents selector.
    */
  override def hasParentSelectorComponent: Boolean = firstSelector.hasParentSelectorComponent || secondSelector.hasParentSelectorComponent

}
