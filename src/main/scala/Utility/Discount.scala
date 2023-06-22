package Utility

enum Discount(val discountValue: Double) {
  case  SENIOR extends Discount(0.02)
  case  ACADEMIC extends Discount(0.05)
  case  STUDENT extends Discount(0.03)
  case  FULL extends Discount(0.00)
  case  DISABILITY extends Discount(0.10)

  def getDiscountByString(discountString: String): Option[Discount] = {
    discountString.toUpperCase match {
      case "SENIOR" => Some(SENIOR)
      case "ACADEMIC" => Some(ACADEMIC)
      case "STUDENT" => Some(STUDENT)
      case "FULL" => Some(FULL)
      case "DISABILITY" => Some(DISABILITY)
      case _ => None
    }
  }
}
