package sless.ast

import sless.ast.base.components.PropertyComponent
import sless.ast.base.creators.CssImplementationCreator
import sless.dsl.{Compilable, PropertyDSL, SelectorDSL, ValueDSL}

object CssImplementation {
  type DSL = PropertyDSL with SelectorDSL with ValueDSL with Compilable
  val dsl: DSL = new CssImplementationCreator()
}

object MijnApp {
  import CssImplementation.dsl._
  def main(args: Array[String]): Unit = {
    val test: PropertyComponent = new PropertyComponent("margin-top")
    print(test.getMarginPosition())
  }

}
