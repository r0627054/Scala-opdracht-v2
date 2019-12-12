package sless.ast.base.components.selector.modifiers

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

abstract class ModifierComponent(val selector: SelectorComponent, val string: String) extends SelectorComponent {
  override def basic(): String = selector.basic()

  override def pretty(spaces: Int): String = selector.pretty(spaces)
}
