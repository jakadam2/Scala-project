package Ticket
import scala.collection.mutable.ListBuffer

class RouteNumberFilter(private val routeNumber: String) extends TicketFilter {
  override def applyFilter(tickets: ListBuffer[Ticket]): ListBuffer[Ticket] = {
    tickets.filter(_.routeNumber == routeNumber)
  }
}