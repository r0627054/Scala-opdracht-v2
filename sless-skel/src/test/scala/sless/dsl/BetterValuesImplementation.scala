package sless.dsl

import sless.ast.base.creators.CssImplementationCreator

object BetterValuesImplementation {
  type DSL = PropertyDSL with SelectorDSL with ValueDSL with CommentDSL with BetterValuesDSL with Compilable
  val dsl: DSL = new CssImplementationCreator()
}
