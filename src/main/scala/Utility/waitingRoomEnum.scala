package Utility

enum waitingRoomEnum(val path: String) {
  case P1 extends waitingRoomEnum("./Data/Examples/1.jpg")
  case P2 extends waitingRoomEnum("./Data/Examples/2.jpg")
  case P3 extends waitingRoomEnum("./Data/Examples/3.jpeg")
  case P4 extends waitingRoomEnum("./Data/Examples/4.jpg")
  case P5 extends waitingRoomEnum("./Data/Examples/5.jpg")
  case P6 extends waitingRoomEnum("./Data/Examples/6.jpg")
}
