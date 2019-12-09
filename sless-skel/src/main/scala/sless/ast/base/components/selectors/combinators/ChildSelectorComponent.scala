package sless.ast.base.components.selectors.combinators

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

class ChildSelectorComponent(override val firstSelector: SelectorComponent, override val secondSelector:SelectorComponent)
  extends CombinatorSelectorComponent(firstSelector,secondSelector){
  override def compile(sheet: CssComponent): String = toStringCompileWithMiddleCharacter(">",sheet)

  override def pretty(sheet: CssComponent, spaces: Int): String = toStringPrettyMiddleCharacterWithExtraSpaces(">",sheet,spaces)
}
