package sless.ast.base

/**
  * The comment format trait contains all the methods which need to be implemented to format a comment of a component.
  * It contains a method to print a comment of a component.
  */
trait CommentFormat {

  /**
    * Given a comment, the comment string is created.
    *
    * @param comment the comment which need to be formatted.
    * @return The comment formatted in the right string.
    */
  def format(comment: String): String = {
    "/* " + comment + " */"
  }
}
