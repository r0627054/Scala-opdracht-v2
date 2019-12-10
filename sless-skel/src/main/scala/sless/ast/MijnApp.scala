package sless.ast

import sless.ast.base.components.PropertyComponent
import sless.ast.base.creators.CssImplementationCreator
import sless.dsl.{CommentDSL, Compilable, PropertyDSL, SelectorDSL, ValueDSL}

object CommentImplementation {
  type DSL = PropertyDSL with SelectorDSL with ValueDSL with CommentDSL with Compilable
  val dsl: DSL = new CssImplementationCreator()
}


object MijnApp {
  import CommentImplementation.dsl._
  def main(args: Array[String]): Unit = {

      val backgroundColor = prop("background-color")
      val container = tipe("div") ## "container"

      val ex = css(
        (N(All.c("class-name1"), All.c("class-name2")) {
          prop("width") := value("100%")
        }).comment( "something with class 1 and 2"),
        container {
          (backgroundColor := value("blue")).comment("bg is blue")
        })
    print(compile(ex))

  }

}
