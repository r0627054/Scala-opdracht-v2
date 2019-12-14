package sless.ast.base.components.value

import sless.ast.base.components.BaseComponent

/**
  * A value component represents the possible value of a declaration.
  *
  * A value component can be basic or can be property specific.
  * Basic value components are: px,in,em,inherit,...
  * Property specific value components are margin value components.
  */
abstract class ValueComponent extends BaseComponent {

  /**
    * Returns the string representation of the value.
    *
    * @return The string representation of the value.
    */
  def getStringValue: String

  /**
    * Returns the basic representation of the value. By default is this the same as "getStringValue".
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = getStringValue

  /**
    * Returns the pretty representation of the value. By default is this the same as "getStringValue".
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = getStringValue
}
