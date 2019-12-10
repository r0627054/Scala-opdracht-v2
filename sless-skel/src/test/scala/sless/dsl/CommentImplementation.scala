package sless.dsl

import sless.ast.base.creators.CssImplementationCreator

object CommentImplementation {
  type DSL = PropertyDSL with SelectorDSL with ValueDSL with CommentDSL with Compilable
  val dsl: DSL = new CssImplementationCreator()
}
