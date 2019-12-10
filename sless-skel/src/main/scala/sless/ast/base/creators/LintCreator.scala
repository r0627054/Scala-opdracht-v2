package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.{CssComponent, RuleComponent}
import sless.dsl.LintDSL

trait LintCreator extends BaseAST with LintDSL {
  /**
    * Check if the given sheet has any style rules without declarations, i.e. of the form "selector {}"
    */
  override def removeEmptyRules(css: CssComponent): (Boolean, CssComponent) = {
      css.removeEmptyRules()
  }

  /**
    * Check if the given sheet has any style rules with a  declaration for all four properties from the set
    * margin-left, margin-right, margin-top, and margin-bottom, and if so, replaces each property by
    * the single shorthand property margin. The new margin property takes the place of the first declaration in order of appearance.
    * The values from the individual properties are aggregated in the order top-right-bottom-left, with spaces in between.
    */
  override def aggregateMargins(css: CssComponent): (Boolean, CssComponent) = {
    css.aggregateMargins(css)
  }

  /**
    * Check if the given sheet contains strictly more than n 'float' properties and, if so, returns true, otherwise false.
    */
  override def limitFloats(css: CssComponent, n: Integer): Boolean = ???
}
