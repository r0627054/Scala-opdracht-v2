package sless.ast.base.components.selectors.combinators

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

abstract class CombinatorSelectorComponent(val firstSelector: SelectorComponent, val secondSelector:SelectorComponent) extends SelectorComponent {
  def toStringCompileWithMiddleCharacter(middleCharacter: String, sheet: CssComponent) : String                        = firstSelector.compile(sheet) + middleCharacter + secondSelector.compile(sheet)
  def toStringPrettyMiddleCharacterWithExtraSpaces(middleCharacter: String, sheet: CssComponent, spaces: Int):String   = firstSelector.pretty(sheet,spaces) + " " + middleCharacter + " " + secondSelector.pretty(sheet,spaces)
  def toStringPrettyMiddleCharacterWithoutExtraSpaces(middleCharacter: String, sheet: CssComponent, spaces: Int):String= firstSelector.pretty(sheet,spaces) +  middleCharacter +  secondSelector.pretty(sheet,spaces)
}
