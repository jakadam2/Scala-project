package Ticket
import Utility.*
import Users.*
import java.util.Calendar

class Ticket(val discount:Discount,// who is let to use ticket
             val nrOfTicket:Int,//  inner ordering number of ticket
             val ticketTime:Int,//  how much seconds left to out dating
             val timeOfBuy:dateStruct,// time when tickets was bought
             val routeNumber:String,//
             var user:User,
             val city:String,
             val ticketURL:String
            )
{
  def timeLeft(): dateStruct={
    return dateStruct(0,0,0)
  }
  def isValid(): Boolean = {
    
      true
  }
  

}

