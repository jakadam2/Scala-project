package Ticket

class TimeLeftFilter(private val threshold: Int) extends TicketFilter {
  override def applyFilter(tickets: List[Ticket]): List[Ticket] = {
    tickets.filter(ticket => {
      val timeLeft = ticket.timeLeft()
      timeLeft.hour * 3600 + timeLeft.minute * 60 + timeLeft.seconds <= threshold
    })
  }
}