package sless.ast.base.creators

import sless.ast.base.components.CssComponent
import sless.dsl.Compilable

class CssImplementationCreator extends PropertyCreator with SelectorCreator with ValueCreator with Compilable {
  override def compile(sheet: CssComponent): String = sheet.compile(sheet)

  override def pretty(sheet: CssComponent, spaces: Int): String = sheet.pretty(sheet,spaces)
}
