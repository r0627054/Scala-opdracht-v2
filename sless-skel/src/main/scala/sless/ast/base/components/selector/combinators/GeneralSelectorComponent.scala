package sless.ast.base.components.selector.combinators

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent


case class GeneralSelectorComponent(override val firstSelector: SelectorComponent, override val secondSelector:SelectorComponent)
  extends CombinatorSelectorComponent(firstSelector,secondSelector){
  override def basic(): String = toStringCompileWithMiddleCharacter("~")

  override def pretty(spaces: Int): String = toStringPrettyMiddleCharacterWithExtraSpaces("~", spaces)

  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = {}
}
