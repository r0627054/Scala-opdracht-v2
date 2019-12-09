package sless.ast.base.components.selectors.modifiers

import sless.ast.base.components.selectors.SelectorComponent

abstract class ModifierComponent(val selector: SelectorComponent, val string: String) extends SelectorComponent {

}
