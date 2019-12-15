package sless.ast.base.components.rule

import sless.ast.base.CommentFormat
import sless.ast.base.components.RuleOrDeclarationComponent
import sless.ast.base.components.selector.SelectorComponent

/**
  * A Rule Component with comment is a rule component which has a comment and implements the Comment Format trait.
  *
  * @param selector     The selector of the rule.
  * @param declarations The declarations of the rule.
  * @param comment      The comment of the rule.
  */
class RuleWithCommentComponent(override val selector: SelectorComponent, declarations: Seq[RuleOrDeclarationComponent], val comment: String)
  extends RuleComponent(selector, declarations) with CommentFormat {

  /**
    * Creates a rule component with a comment given a rule component and a comment.
    *
    * @param ruleComponent The rule component which needs to be extended with a comment.
    * @param comment       The comment which needs to be added to the rule component.
    */
  def this(ruleComponent: RuleComponent, comment: String) {
    this(ruleComponent.selector, ruleComponent.declarations, comment)
  }

  /**
    * Returns the formatted comment string followed by the basic method of the rule component super class.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = {
    format(comment) + super.basic()
  }

  /**
    * Returns the formatted comment string followed by the pretty method of the rule component super class.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = {
    format(comment) + "\n" + super.pretty(spaces)
  }

  /**
    * This function is a helper function which contains the main logic of the both toBasicComponents methods.
    * The difference is that this needs to create a comment with it.
    *
    * @param sel The selector component which will be resolved as a parent.
    * @return The Seq of basic RuleComponents with their basic DeclarationComponents.
    */
  override protected def toBasicComponentsHelper(sel: SelectorComponent): Seq[RuleComponent] = {
    val plainDeclarationsOfCurrentRule: Seq[RuleOrDeclarationComponent] = getPlainDeclarationOfCurrentRule(sel)
    if (plainDeclarationsOfCurrentRule.isEmpty) getInnerRules(sel) else new RuleWithCommentComponent(sel, plainDeclarationsOfCurrentRule, comment) +: getInnerRules(sel)
  }
}
