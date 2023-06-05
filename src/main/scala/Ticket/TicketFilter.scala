package Ticket



trait TicketFilter {
  def applyFilter(tickets: List[Ticket]): List[Ticket]
}
