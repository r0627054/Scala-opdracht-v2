package sless.dsl

import sless.ast.base.creators.CssImplementationCreator

object LessNestingImplementation {
  type DSL = PropertyDSL with NestedSelectorDSL with ValueDSL with Compilable
  val dsl: DSL = new CssImplementationCreator()
}
