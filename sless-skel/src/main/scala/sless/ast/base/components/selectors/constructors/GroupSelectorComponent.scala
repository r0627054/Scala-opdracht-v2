package sless.ast.base.components.selectors.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

class GroupSelectorComponent(val selectors: Seq[SelectorComponent]) extends SelectorComponent{
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



}
