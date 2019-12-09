package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.{DeclarationComponent, PropertyComponent}
import sless.dsl.PropertyDSL

trait PropertyCreator extends BaseAST with PropertyDSL{
  override def prop(string: String): Property = new PropertyComponent(string)

  override protected def assign(p: Property, value: Value): Declaration = new DeclarationComponent(p,value)
}
