package sless.ast.base.components.selector.combinators

import sless.ast.base.components.selector.SelectorComponent

case class DescendantSelectorComponent(override val firstSelector: SelectorComponent, override val secondSelector:SelectorComponent)
  extends CombinatorSelectorComponent(firstSelector,secondSelector){
  override def basic(): String = toStringCompileWithMiddleCharacter(" ")

  override def pretty(spaces: Int): String = toStringPrettyMiddleCharacterWithoutExtraSpaces(" ",spaces)

  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = {
    new DescendantSelectorComponent(firstSelector.replaceParentWithSelectorComponent(parentSelector),secondSelector.replaceParentWithSelectorComponent(parentSelector))
  }
}
