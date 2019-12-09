package sless.ast.base

import sless.dsl.ValueDSL

class ValueAST(val value: String) extends ValueDSL{
  override def value(string: String): Value = new ValueAST(string)
}
