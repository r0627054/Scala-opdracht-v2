package sless.dsl

import sless.ast.base.components.{PropertyComponent, ValueComponent}
import sless.ast.base.components.declaration.DeclarationComponent
import sless.ast.base.creators.CssImplementationCreator

object MixinImplementation {
  type DSL = PropertyDSL with SelectorDSL with ValueDSL with MixinDSL with Compilable
  val dsl: DSL = new CssImplementationCreator()

  import MixinImplementation.dsl._

  /**
    * Create a non-parametric mixin that contains the declarations:
    *     color: red;
    *     background: white;
    */
  val simpleColorMixin : Seq[dsl.Declaration] = {
    val color = prop("background-color")
    val red = value("red")
    Seq(createDeclaration("color","red"), createDeclaration("background","white"))
  }

  /**
    * Create a parametric mixin that takes a $mixin and an argument $padding as arguments, and consists of the following declarations:
    *     $mixin **i.e., mixin's declarations come first**
    *     margin: $padding;
    */
  val nestedMixin : (Seq[dsl.Declaration], String) => Seq[dsl.Declaration] = (mixin: Seq[dsl.Declaration], padding: String) => ???

  /**
    * NOTE: Remember this assignment? Without realizing it, you already implemented a kind of parametric mixin earlier while experimenting with variables,
    * but we did not have convenient mixin application syntax back then. Reimplement your mixin here, but as a mixin value.
    *
    * Create a sequence of declaration for the given height that results in an aspect ratio of 2/1
    *     height: $height;
    *     width: $height * 2;
    */
   val doubledWidthMixin : Int => Seq[dsl.Declaration] = (height: Int) => ???


  private def createDeclaration(property: String, va : String) : dsl.Declaration = {
    prop(property) := value(va)
  }


}
