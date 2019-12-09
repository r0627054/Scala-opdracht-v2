package sless.ast.base.components.selectors.combinators

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent


class AdjacentSelectorComponent(override val firstSelector: SelectorComponent, override val secondSelector:SelectorComponent)
  extends CombinatorSelectorComponent(firstSelector,secondSelector){
  override def compile(sheet: CssComponent): String = ???

  override def pretty(sheet: CssComponent, spaces: Int): String = ???
}
