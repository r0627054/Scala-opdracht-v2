package sless.dsl

import sless.ast.base.creators.CssImplementationCreator

object CssImplementation {
  type DSL = PropertyDSL with SelectorDSL with ValueDSL with Compilable
  val dsl: DSL = new CssImplementationCreator()
}
