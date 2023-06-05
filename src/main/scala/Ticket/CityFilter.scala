package Ticket

class CityFilter(private val city: String) extends TicketFilter {
  override def applyFilter(tickets: List[Ticket]): List[Ticket] = {
    tickets.filter(_.city == city)
  }
}