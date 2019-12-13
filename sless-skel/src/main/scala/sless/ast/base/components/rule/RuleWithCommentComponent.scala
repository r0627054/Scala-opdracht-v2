package sless.ast.base.components.rule

import sless.ast.base.CommentFormat
import sless.ast.base.components.{CssComponent, RuleOrDeclarationComponent}
import sless.ast.base.components.declaration.DeclarationComponent
import sless.ast.base.components.selector.SelectorComponent

class RuleWithCommentComponent(override val selector: SelectorComponent, declarations: Seq[RuleOrDeclarationComponent], val comment: String)
  extends RuleComponent(selector,declarations) with CommentFormat {

  def this(ruleComponent: RuleComponent, comment: String) {
    this(ruleComponent.selector,ruleComponent.declarations,comment)
  }

  override def basic(): String = {
    format(comment) + super.basic()
  }

  override def pretty(spaces: Int): String = {
    format(comment) + "\n" + super.pretty(spaces)
  }

  // TODO schrijf een tobasic method
  override def toBasicComponents(): Seq[RuleComponent] = {
    //this Seq contains the basic rules and the declarations of the current rule
    val plainDeclarationsOfCurrentRule : Seq[RuleOrDeclarationComponent] = getPlainDeclarationOfCurrentRule()
    if(plainDeclarationsOfCurrentRule.isEmpty)  getInnerRules() else new RuleWithCommentComponent(selector,plainDeclarationsOfCurrentRule,comment) +: getInnerRules()
  }


}
