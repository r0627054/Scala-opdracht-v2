package sless.dsl

import sless.ast.base.creators.CssImplementationCreator

object ExtendImplementation {
  type DSL = PropertyDSL with SelectorDSL with ValueDSL with ExtendDSL with Compilable
  val dsl: DSL = new CssImplementationCreator()
}
