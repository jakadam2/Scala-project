package Ticket

import scala.collection.mutable.ListBuffer

class CompositeFilter(private val filters: ListBuffer[TicketFilter]) extends TicketFilter {
  override def applyFilter(tickets: ListBuffer[Ticket]): ListBuffer[Ticket] = {
    filters.foldLeft(tickets)((filteredTickets, filter) => filter.applyFilter(filteredTickets))
  }
}
