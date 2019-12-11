package sless.ast.base.components.selectors.modifiers

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selectors.SelectorComponent

abstract class ModifierComponent(val selector: SelectorComponent, val string: String) extends SelectorComponent {
  override def basic(): String = selector.basic()

  override def pretty(spaces: Int): String = selector.pretty(spaces)
}
