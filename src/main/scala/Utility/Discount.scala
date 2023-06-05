package Utility

enum Discount(val discointValue: Double):
  case SENIOR extends Discount(0.02)
  case ACADEMIC extends Discount(0.05)
  case STUDENT extends Discount(0.03)
  case FULL extends Discount(0.00)
  case DISABILITY extends Discount(0.10)

