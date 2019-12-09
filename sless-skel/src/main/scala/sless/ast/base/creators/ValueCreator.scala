package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.ValueComponent
import sless.dsl.ValueDSL


trait ValueCreator extends BaseAST with ValueDSL {
  override def value(string: String): Value = new ValueComponent(string)
}
