package sless.ast.base.components.selectors.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

class GroupSelectorComponent(val selectors: Seq[SelectorComponent]) extends SelectorComponent{
  override def compile(sheet: CssComponent): String = {
    var result : String = ""
    val lastIndex : Int = if(selectors.nonEmpty) selectors.length - 1 else 0
    for((selector,index) <- selectors.view.zipWithIndex) {
      result += selector.compile(sheet)
      if(index != lastIndex) result += ","
    }
    result
  }

  override def pretty(sheet: CssComponent, spaces: Int): String = {
    var result : String = ""
    val lastIndex : Int = if(selectors.nonEmpty) selectors.length - 1 else 0
    for((selector,index) <- selectors.view.zipWithIndex) {
      result += selector.pretty(sheet,spaces)
      if(index != lastIndex) result += ", "
    }
    result
  }



}
