package sless.ast.base.components.selector
import sless.ast.base.components.CssComponent

case class ParentSelectorComponent() extends SelectorComponent {
  /**
    * A parent selector cannot be extended. This is because it first needs to be resolved before it can be extended.
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = throw new IllegalAccessError("A Parent selector component cannot be extended. It should be resolved first.")

  override def basic(): String = throw new IllegalAccessError("A Parent selector component cannot be printed by basic. It should be resolved first")

  override def pretty(spaces: Int): String = throw new IllegalAccessError("A Parent selector component cannot be extended. It should be resolved first.")

  //the actual replacement
  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = parentSelector
}
