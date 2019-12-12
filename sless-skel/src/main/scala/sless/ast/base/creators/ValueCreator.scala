package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.value.ValueComponent
import sless.ast.base.components.value.basic.BasicValueComponent
import sless.dsl.ValueDSL


trait ValueCreator extends BaseAST with ValueDSL {
  override def value(string: String): Value = new BasicValueComponent(string)
}
