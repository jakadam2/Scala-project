package Ticket
import scala.collection.mutable.ListBuffer
import Utility.dateStruct
class TimeLeftFilter(private val threshold: Int) extends TicketFilter {
  override def applyFilter(tickets: ListBuffer[Ticket]): ListBuffer[Ticket] = {
    tickets.filter(ticket => {
      val timeLeft = ticket.timeLeft()
      timeLeft.hour * 3600 + timeLeft.minute * 60 + timeLeft.seconds <= threshold
    })
  }
}