package sless.ast.base.components.value.basic

import sless.ast.base.components.value.ValueComponent

/**
  * The lengthValue component is a value component representing a absolute or relative length.
  * The length component itself is abstract and all its subclasses are case classes.
  * Examples of absolute lengths are: px, pt,...
  * Examples of relative lengths are: %, em,...
  *
  * @param length The actual length of the value component.
  */
abstract class LengthValueComponent(val length: Double) extends ValueComponent {
  /**
    * The string value is equals the string value of the double length value.
    *
    * @return The string representation of the value.
    */
  override def getStringValue: String = length.toString
}

/**
  * This length value component is represented in pixels.
  *
  * @param length The actual length of the value component.
  */
case class PixelUnitValueComponent(override val length: Double) extends LengthValueComponent(length) {
  /**
    * The string value equals the string value of the double length value followed by "px"
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + "px"
}

/**
  * This length value component is represented in picas.
  *
  * @param length The actual length of the value component.
  */
case class PicasValueComponent(override val length: Double) extends LengthValueComponent(length) {
  /**
    * The string value equals the string value of the double length value followed by "pc"
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + "pc"
}

/**
  * This length value component is represented in points.
  *
  * @param length The actual length of the value component.
  */
case class PointsValueComponent(override val length: Double) extends LengthValueComponent(length) {
  /**
    * The string value equals the string value of the double length value followed by "pt"
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + "pt"
}

/**
  * This length value component is represented in millimeter.
  *
  * @param length The actual length of the value component.
  */
case class MilliMeterValueComponent(override val length: Double) extends LengthValueComponent(length) {
  /**
    * The string value equals the string value of the double length value followed by "mm"
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + "mm"
}

/**
  * This length value component is represented in centimeter.
  *
  * @param length The actual length of the value component.
  */
case class CentiMeterValueComponent(override val length: Double) extends LengthValueComponent(length) {
  /**
    * The string value equals the string value of the double length value followed by "cm"
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + "cm"
}

/**
  * This length value component is represented in inches.
  *
  * @param length The actual length of the value component.
  */
case class InchesValueComponent(override val length: Double) extends LengthValueComponent(length) {
  /**
    * The string value equals the string value of the double length value followed by "in"
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + "in"
}

/**
  * This length value component is represented in em.
  *
  * @param length The actual length of the value component.
  */
case class EmValueComponent(override val length: Double) extends LengthValueComponent(length) {
  /**
    * The string value equals the string value of the double length value followed by "em"
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + "em"
}

/**
  * This length value component is represented in ex.
  *
  * @param length The actual length of the value component.
  */
case class ExValueComponent(override val length: Double) extends LengthValueComponent(length) {
  /**
    * The string value equals the string value of the double length value followed by "ex"
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + "ex"
}

/**
  * This length value component is represented in percentages.
  *
  * @param length The actual length of the value component.
  */
case class PercentageValueComponent(override val length: Double) extends LengthValueComponent(length) {
  /**
    * The string value equals the string value of the double length value followed by "%"
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = super.basic() + "%"
}