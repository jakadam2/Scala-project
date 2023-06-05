package Ticket


class CompositeFilter(private val filters: List[TicketFilter]) extends TicketFilter {
  override def applyFilter(tickets: List[Ticket]): List[Ticket] = {
    filters.foldLeft(tickets)((filteredTickets, filter) => filter.applyFilter(filteredTickets))
  }
}
