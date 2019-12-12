package sless.ast.base.components.value.basic

import sless.ast.base.components.value.ValueComponent

abstract class LengthValueComponent(val length: Double) extends ValueComponent {
  override def getStringValue(): String = length.toString
}

case class PixelUnitValueComponent(override val length: Double) extends LengthValueComponent(length) {
  override def basic(): String = super.basic() + "px"
}

case class PicasValueComponent(override val length: Double) extends LengthValueComponent(length) {
  override def basic(): String = super.basic() + "pc"
}

case class PointsValueComponent(override val length: Double) extends LengthValueComponent(length) {
  override def basic(): String = super.basic() + "pt"
}

case class MilliMeterValueComponent(override val length: Double) extends LengthValueComponent(length) {
  override def basic(): String = super.basic() + "mm"
}

case class CentiMeterValueComponent(override val length: Double) extends LengthValueComponent(length) {
  override def basic(): String = super.basic() + "cm"
}

case class InchesValueComponent(override val length: Double) extends LengthValueComponent(length) {
  override def basic(): String = super.basic() + "in"
}

case class EmValueComponent(override val length: Double) extends LengthValueComponent(length) {
  override def basic(): String = super.basic() + "em"
}

case class ExValueComponent(override val length: Double) extends LengthValueComponent(length) {
  override def basic(): String = super.basic() + "ex"
}

case class PercentageValueComponent(override val length: Double) extends LengthValueComponent(length) {
  override def basic(): String = super.basic() + "%"
}