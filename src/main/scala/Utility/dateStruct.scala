package Utility

class dateStruct (val hour:Int,val minute:Int,val seconds:Int) {

  private val myVal =  3600 * hour + 60 * minute + seconds
  def subtract(other: dateStruct) = {
    var subVal = myVal - other.myVal
    val hours = subVal/3600.toInt
    subVal %= 3600
    val minutes = subVal/60.toInt
    subVal %= 60
    dateStruct(hours,minutes,subVal)
  }
  def getmyVal(): Int ={
    return myVal;
  }
}
