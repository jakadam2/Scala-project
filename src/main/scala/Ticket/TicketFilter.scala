package Ticket



import scala.collection.mutable.ListBuffer

trait TicketFilter {
  def applyFilter(tickets: ListBuffer[Ticket]): ListBuffer[Ticket]
}
