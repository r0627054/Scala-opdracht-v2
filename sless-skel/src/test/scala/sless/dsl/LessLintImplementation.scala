package sless.dsl

import sless.ast.base.creators.CssImplementationCreator

object LessLintImplementation {
  type DSL = PropertyDSL with SelectorDSL with ValueDSL with LintDSL with Compilable
  val dsl: DSL = new CssImplementationCreator()
}
