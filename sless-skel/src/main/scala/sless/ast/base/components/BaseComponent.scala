package sless.ast.base.components

import sless.ast.base.{BaseAST, Printable}

/**
  * A baseComponent can be considered as the building block of all other components.
  * Every other component needs to implement the Printable methods and needs the BaseAST implementation.
  */
abstract class BaseComponent extends BaseAST with Printable {

}
