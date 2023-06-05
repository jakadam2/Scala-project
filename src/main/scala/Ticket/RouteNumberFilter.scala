package Ticket

class RouteNumberFilter(private val routeNumber: String) extends TicketFilter {
  override def applyFilter(tickets: List[Ticket]): List[Ticket] = {
    tickets.filter(_.routeNumber == routeNumber)
  }
}