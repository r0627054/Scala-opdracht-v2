package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.CssComponent
import sless.dsl.Compilable

/**
  * A compilable creator is a creator class which implements the Compilable methods.
  */
trait CompilableCreator extends BaseAST with Compilable {
  override def compile(sheet: CssComponent): String = sheet.basic()

  override def pretty(sheet: CssComponent, spaces: Int): String = sheet.pretty(spaces)
}
