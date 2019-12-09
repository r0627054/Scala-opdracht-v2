package sless.ast.base.components.selectors.combinators

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

class DescendantSelectorComponent(override val firstSelector: SelectorComponent, override val secondSelector:SelectorComponent)
  extends CombinatorSelectorComponent(firstSelector,secondSelector){
  override def compile(sheet: CssComponent): String = toStringWithMiddleCharacter(" ",sheet)

  override def pretty(sheet: CssComponent, spaces: Int): String = ???
}
