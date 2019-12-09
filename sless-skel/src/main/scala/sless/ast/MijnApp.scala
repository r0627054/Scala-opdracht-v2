package sless.ast

import sless.ast.base.creators.CssImplementationCreator
import sless.dsl.{Compilable, PropertyDSL, SelectorDSL, ValueDSL}

object CssImplementation {
  type DSL = PropertyDSL with SelectorDSL with ValueDSL with Compilable
  val dsl: DSL = new CssImplementationCreator()
}

object MijnApp {
  import CssImplementation.dsl._
  def main(args: Array[String]): Unit = {
    val backgroundColor = prop("background-color")
    val container = tipe("div") ## "container"

    val ex = css(
      N(All.c("class-name1"), All.c("class-name2")) {
        prop("width") := value("100%")
      },
      container {
        backgroundColor := value("blue")
      },
      (container :| "hover") {
        backgroundColor := value("red")
      }
    )

    println(CssImplementation.dsl.pretty(ex,4))
  }

}
