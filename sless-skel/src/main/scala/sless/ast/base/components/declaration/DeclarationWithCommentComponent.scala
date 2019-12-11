package sless.ast.base.components.declaration


import sless.ast.base.CommentFormat
import sless.ast.base.components.{CssComponent, PropertyComponent, ValueComponent}

class DeclarationWithCommentComponent(override val prop: PropertyComponent, override val value: ValueComponent, val comment : String)
  extends DeclarationComponent(prop,value) with CommentFormat {

  def this(declarationComponent: DeclarationComponent, comment: String) {
    this(declarationComponent.prop,declarationComponent.value,comment)
  }

  override def basic(): String = {
     super.basic() + format(comment)
  }

  override def pretty(spaces: Int): String = {
    super.pretty(spaces) + " " + format(comment)
  }



}
