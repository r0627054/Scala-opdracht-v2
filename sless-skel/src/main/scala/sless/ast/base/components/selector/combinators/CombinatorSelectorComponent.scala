package sless.ast.base.components.selector.combinators

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

abstract class CombinatorSelectorComponent(val firstSelector: SelectorComponent, val secondSelector:SelectorComponent) extends SelectorComponent {
  def toStringCompileWithMiddleCharacter(middleCharacter: String) : String                        = firstSelector.basic() + middleCharacter + secondSelector.basic()
  def toStringPrettyMiddleCharacterWithExtraSpaces(middleCharacter: String, spaces: Int):String   = firstSelector.pretty(spaces) + " " + middleCharacter + " " + secondSelector.pretty(spaces)
  def toStringPrettyMiddleCharacterWithoutExtraSpaces(middleCharacter: String, spaces: Int):String= firstSelector.pretty(spaces) +  middleCharacter +  secondSelector.pretty(spaces)
}
