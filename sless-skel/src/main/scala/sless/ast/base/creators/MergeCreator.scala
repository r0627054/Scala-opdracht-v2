package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.CssComponent
import sless.dsl.MergeDSL

/**
  * A merge creator is a creator class which implements the MergeDSL methods.
  */
trait MergeCreator extends BaseAST with MergeDSL {
  def mergeSheets(cssSheets: Css*): Css = {
    val allCssSheets = cssSheets.toSeq
    if (allCssSheets.isEmpty) {
      new CssComponent(Seq())
    } else {
      allCssSheets.head.mergeSheets(allCssSheets.tail)
    }
  }
}
