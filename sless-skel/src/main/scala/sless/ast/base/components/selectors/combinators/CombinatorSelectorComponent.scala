package sless.ast.base.components.selectors.combinators

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

abstract class CombinatorSelectorComponent(val firstSelector: SelectorComponent, val secondSelector:SelectorComponent) extends SelectorComponent {
  def toStringWithMiddleCharacter(middleCharacter: String, sheet: CssComponent) : String =  firstSelector.compile(sheet) + middleCharacter + secondSelector.compile(sheet)


}
