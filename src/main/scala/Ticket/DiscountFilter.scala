package Ticket

class DiscountFilter(private val discount: Double) extends TicketFilter {
  override def applyFilter(tickets: List[Ticket]): List[Ticket] = {
    tickets.filter(_.discount >= discount)
  }
}