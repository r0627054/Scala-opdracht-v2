package sless.ast.base.components.selector

import sless.ast.base.components.{BaseComponent, CssComponent}


trait SelectorComponent extends BaseComponent {

  /**
    * This method executes the extending of a selector.
    * In case of Combinator Selectors or Group Selectors the method will be called on both subSelector.
    * It is called in this way that the tree is executed from the right to the left.
    *
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  def extendSelectorReplacement(css: CssComponent): CssComponent

  //return a new selectorComponent
  def replaceParentWithSelectorComponent(parentSelector: SelectorComponent) : SelectorComponent

  //checks if the selector contains/is a parent selector
  def hasParentSelectorComponent(): Boolean
}
