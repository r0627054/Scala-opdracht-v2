package sless.ast.base.components

import sless.ast.base.enumeration.MarginType

/**
  * The PropertyComponent represents the actual property of a declaration.
  * This class contains the name of the actual property.
  *
  * @param propertyName The name of the property.
  */
class PropertyComponent(val propertyName: String) extends BaseComponent {

  /**
    * Print the name of the property.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = propertyName

  /**
    * Print the name of the property, no spaces are needed.
    *
    * @param spaces The number of spaces places before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = propertyName

  /**
    * Checks whether tis property is a margin property.
    *
    * @return True when this is a margin property; otherwise false.
    */
  def containsMarginProperty(): Boolean = {
    propertyName.startsWith("margin-")
  }

  /**
    * Returns the margin position of the property.
    *
    * @throws IllegalArgumentException When this property is not a margin property.
    * @return the margin Type of the property.
    */
  def getMarginPosition(): MarginType.Value = {
    if (!this.containsMarginProperty()) {
      throw new IllegalArgumentException("This is not a margin property!")
    }
    MarginType.withName(propertyName.substring(7))
  }

  /**
    * Checks whether the given string equals the name of the property.
    *
    * @param name The name to which it needs to be compared.
    * @return True when string equals the name of the property otherwise false.
    */
  def equalsPropertyName(name: String): Boolean = {
    name == propertyName
  }

  /**
    * Returns the name of the property.
    *
    * @return The name of the property.
    */
  def getPropertyName(): String = propertyName

}
