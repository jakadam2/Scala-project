package Ticket
import Utility.*
import Users.*
import java.util.Calendar

class Ticket(val discount:Discount,// who is let to use ticket
             val nrOfTicket:Int,//  inner ordering number of ticket
             val ticketTime:dateStruct,//  how much seconds left to out dating
             val timeOfBuy:dateStruct,// time when tickets was bought
             val routeNumber:String,//
             val city:String,
             var user:User,
             var ticketURL:String,
             var ticketFileName:String=""
            )
{
  def this(user: User,city:String,routeNr: String) = {
    this(user.discount,0,dateStruct(0,20,0),dateStruct(12,0,0),routeNr,city,user,user.userFolderUrl)
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
      timeLeft().hour >= 0 && timeLeft().seconds >= 0 && timeLeft().minute >= 0
  }
  

}

