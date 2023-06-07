package Ticket
import scala.collection.mutable.ListBuffer

class CityFilter(private val city: String) extends TicketFilter {
  override def applyFilter(tickets: ListBuffer[Ticket]): ListBuffer[Ticket] = {
    tickets.filter(_.city == city)
  }
}