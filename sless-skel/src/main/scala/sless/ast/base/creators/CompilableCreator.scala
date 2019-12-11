package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.CssComponent
import sless.dsl.Compilable

trait CompilableCreator extends BaseAST with Compilable{
  override def compile(sheet: CssComponent): String = sheet.basic()
  override def pretty(sheet: CssComponent, spaces: Int): String = sheet.pretty(spaces)
}
