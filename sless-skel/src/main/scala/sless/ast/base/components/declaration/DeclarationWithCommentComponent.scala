package sless.ast.base.components.declaration


import sless.ast.base.CommentFormat
import sless.ast.base.components.PropertyComponent
import sless.ast.base.components.value.ValueComponent

/**
  * A declaration component with comment is a declaration component which has a comment and implements the Comment Format trait.
  *
  * @param prop    The property of the declaration.
  * @param value   The value of the declaration.
  * @param comment The comment of the declaration.
  */
class DeclarationWithCommentComponent(override val prop: PropertyComponent, override val value: ValueComponent, val comment: String)
  extends DeclarationComponent(prop, value) with CommentFormat {

  /**
    * It extends the given declaration component with the given comment.
    *
    * @param declarationComponent The declaration component which needs to be extended with the given comment.
    * @param comment              The comment which needs to be added to the declaration component.
    */
  def this(declarationComponent: DeclarationComponent, comment: String) {
    this(declarationComponent.prop, declarationComponent.value, comment)
  }

  /**
    * Returns the basic print of a declaration component without the comment, followed by the formatted comment.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = {
    super.basic() + format(comment)
  }

  /**
    * Returns the pretty print of a declaration component without the comment, followed by the formatted comment.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = {
    super.pretty(spaces) + " " + format(comment)
  }


}
