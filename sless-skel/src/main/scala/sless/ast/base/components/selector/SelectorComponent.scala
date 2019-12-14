package sless.ast.base.components.selector

import sless.ast.base.components.{BaseComponent, CssComponent}

/**
  * The selector component is a part of the rule component.
  * It represent the selector of the rule.
  */
trait SelectorComponent extends BaseComponent {

  /**
    * This method executes the extending of a selector.
    * In case of Combinator Selectors or Group Selectors the method will be called on all sub selectors.
    * It is called in this way that the tree is executed from the right to the left.
    *
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  def extendSelectorReplacement(css: CssComponent): CssComponent

  /**
    * A selector component can contain another parentSelector.
    * This method replaces the parent selector with the given selector.
    *
    * @param parentSelector The selector which will replace the parent selector.
    * @return The Selector in which the parent selector is replaced with the given selector.
    */
  def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent

  /**
    * Checks whether the selector contains a parent selector.
    *
    * @return True when the selector contains the parents selector.
    */
  def hasParentSelectorComponent: Boolean
}
