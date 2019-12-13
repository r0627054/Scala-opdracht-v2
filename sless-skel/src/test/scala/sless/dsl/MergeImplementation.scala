package sless.dsl

import sless.ast.base.creators.CssImplementationCreator

object MergeImplementation {
  type DSL = PropertyDSL with SelectorDSL with ValueDSL with MergeDSL with Compilable
  val dsl: DSL = new CssImplementationCreator
}
