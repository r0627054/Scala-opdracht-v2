package sless.ast.base.components.selector.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

case class GroupSelectorComponent(val selectors: Seq[SelectorComponent]) extends SelectorComponent{
  override def basic(): String = {
    var result : String = ""
    val lastIndex : Int = if(selectors.nonEmpty) selectors.length - 1 else 0
    for((selector,index) <- selectors.view.zipWithIndex) {
      result += selector.basic()
      if(index != lastIndex) result += ","
    }
    result
  }

  override def pretty(spaces: Int): String = {
    var result : String = ""
    val lastIndex : Int = if(selectors.nonEmpty) selectors.length - 1 else 0
    for((selector,index) <- selectors.view.zipWithIndex) {
      result += selector.pretty(spaces)
      if(index != lastIndex) result += ", "
    }
    result
  }

  /**
    * On a group of selector each selector needs to extended starting form the far most selector, ending at the most
    * left selector.
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = {
    var currentCss: CssComponent = css
    for(selectorComponent <- selectors.reverse) {
      currentCss = selectorComponent.extendSelectorReplacement(currentCss)
    }
    currentCss
  }

  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = {
    new GroupSelectorComponent(selectors.map(selector => selector.replaceParentWithSelectorComponent(parentSelector)))
  }
}
