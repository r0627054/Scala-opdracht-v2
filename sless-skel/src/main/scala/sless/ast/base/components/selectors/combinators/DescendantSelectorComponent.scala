package sless.ast.base.components.selectors.combinators

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

class DescendantSelectorComponent(override val firstSelector: SelectorComponent, override val secondSelector:SelectorComponent)
  extends CombinatorSelectorComponent(firstSelector,secondSelector){
  override def basic(): String = toStringCompileWithMiddleCharacter(" ")

  override def pretty(spaces: Int): String = toStringPrettyMiddleCharacterWithoutExtraSpaces(" ",spaces)
}
