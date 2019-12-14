package sless.ast.base.components.selector.combinators

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

abstract class CombinatorSelectorComponent(val firstSelector: SelectorComponent, val secondSelector:SelectorComponent) extends SelectorComponent {
  def toStringCompileWithMiddleCharacter(middleCharacter: String) : String                        = firstSelector.basic() + middleCharacter + secondSelector.basic()
  def toStringPrettyMiddleCharacterWithExtraSpaces(middleCharacter: String, spaces: Int):String   = firstSelector.pretty(spaces) + " " + middleCharacter + " " + secondSelector.pretty(spaces)
  def toStringPrettyMiddleCharacterWithoutExtraSpaces(middleCharacter: String, spaces: Int):String= firstSelector.pretty(spaces) +  middleCharacter +  secondSelector.pretty(spaces)

  /**
    * Extend the selector of the combinator classes.
    * First the right component has to be extended. After this, the left component.
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = {
    val rightCss : CssComponent = secondSelector.extendSelectorReplacement(css)
    firstSelector.extendSelectorReplacement(rightCss)
  }

  override def hasParentSelectorComponent(): Boolean = firstSelector.hasParentSelectorComponent() || secondSelector.hasParentSelectorComponent()

}
