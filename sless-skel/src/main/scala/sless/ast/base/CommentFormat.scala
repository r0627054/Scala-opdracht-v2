package sless.ast.base

trait CommentFormat {
  def format(comment:String): String = {
    "/* " + comment + " */"
  }
}
