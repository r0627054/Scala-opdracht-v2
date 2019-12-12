package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.selector.ExtendedSelectorComponent
import sless.dsl.ExtendDSL

trait ExtendCreator extends BaseAST with ExtendDSL {
  protected def extendI(s : Selector,selector: Selector) : Selector = new ExtendedSelectorComponent(s,selector)
}
