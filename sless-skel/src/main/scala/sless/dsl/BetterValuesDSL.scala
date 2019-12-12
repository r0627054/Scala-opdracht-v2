package sless.dsl

trait BetterValuesDSL extends ValueDSL {

  def inherit: Value
  def auto: Value

  def Margin(value1: Value) : Value
  def Margin(value1: Value,value2: Value) : Value
  def Margin(value1: Value,value2: Value,value3: Value) : Value
  def Margin(value1: Value,value2: Value,value3: Value,value4: Value) : Value

  protected def createPixelValue(pixels: Double) : Value
  protected def createPicasValue(picas: Double) : Value
  protected def createPointsValue(points: Double) : Value
  protected def createMilliMeterValue(mms: Double) : Value
  protected def createCentiMeterValue(cms: Double) : Value
  protected def createInchesValue(inches: Double) : Value
  protected def createEmValue(ems: Double) : Value
  protected def createExValue(exs: Double) : Value
  protected def createPercentageValue(pers: Double) : Value

  implicit class PixelUnitShortHand(pixels: Double) {
    def px: Value = createPixelValue(pixels)
  }

  implicit class PicasUnitShortHand(picase: Double) {
    def pc: Value = createPicasValue(picase)
  }

  implicit class PointUnitShortHand(points: Double) {
    def pt: Value = createPointsValue(points)
  }

  implicit class MilliMeterUnitShortHand(mm: Double) {
    def mm: Value = createMilliMeterValue(mm)
  }

  implicit class CentiMeterUnitShortHand(cm: Double) {
    def cm: Value = createCentiMeterValue(cm)
  }

  implicit class InchesUnitShortHand(inches: Double) {
    def in: Value = createInchesValue(inches)
  }

  implicit class EmUnitShortHand(ems: Double) {
    def em: Value = createEmValue(ems)
  }

  implicit class ExUnitShortHand(exs: Double) {
    def ex: Value = createExValue(exs)
  }

  implicit class PercentageUnitShortHand(percentages: Double) {
    def per: Value = createPercentageValue(percentages)
  }


}
