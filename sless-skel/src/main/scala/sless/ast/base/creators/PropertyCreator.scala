package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.PropertyComponent
import sless.ast.base.components.declaration.DeclarationComponent
import sless.dsl.PropertyDSL

/**
  * A property creator is a creator class which implements the PropertyDSL methods.
  */
trait PropertyCreator extends BaseAST with PropertyDSL {
  override def prop(string: String): Property = new PropertyComponent(string)

  override protected def assign(p: Property, value: Value): Declaration = new DeclarationComponent(p, value)
}
