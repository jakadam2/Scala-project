package Ticket
import Utility.*
import Users.*
import java.util.Calendar

class Ticket(val discount:Discount,
             val nrOfTicket:Int,
             val ticketTime:dateStruct,
             var timeOfBuy:dateStruct,
             val routeNumber:String,
             val city:String,
             var user:User,
             var ticketURL:String,
             var ticketFileName:String=""
            )
{
  def this(user: User,city:String,routeNr: String,path:String,minutes:Int) = {
    this(user.discount,0,dateStruct(0,minutes,0),dateStruct(0,0,0),routeNr,city,user,path)
    val now = Calendar.getInstance()
    val nowStruct = dateStruct(now.get(Calendar.PM), now.get(Calendar.MINUTE), now.get(Calendar.SECOND))
    this.timeOfBuy = nowStruct

  }
  
  ticketFileName = this.ticketURL.split("/").last
  user.addTicket(this)
  def timeLeft(): dateStruct={
    val now = Calendar.getInstance()
    val nowStruct = dateStruct(now.get(Calendar.PM),now.get(Calendar.MINUTE),now.get(Calendar.SECOND))
    val curDiff = nowStruct.subtract(timeOfBuy)
    ticketTime.subtract(curDiff)
  }
  def isValid(): Boolean = {
      timeLeft().hour > 0 && timeLeft().seconds > 0 && timeLeft().minute > 0
  }
  
  

}

