import java.util.Calendar

class Ticket(val discount:Double,// who is let to use ticket
             val nrOfTicket:Int,//  inner ordering number of ticket
             val ticketTime:Int,//  how much seconds left to out dating
             val timeOfBuy:dateStruct,// time when tickets was bought
             val routeNumber:String,//
             val city:String,
             val ticketURL:String)
{
  def isValid(): Boolean = {
    
      true
  }
  
  def timeLeft(): Int = {
    0
  }
}
