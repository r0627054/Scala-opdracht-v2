package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.value.basic.BasicValueComponent
import sless.dsl.ValueDSL

/**
  * A value creator is a creator class which implements the ValueDSl methods and abstract type members.
  */
trait ValueCreator extends BaseAST with ValueDSL {
  override def value(string: String): Value = new BasicValueComponent(string)
}
