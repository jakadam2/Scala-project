package Ticket
import scala.collection.mutable.ListBuffer

class DiscountFilter(private val discount: Double) extends TicketFilter {
  override def applyFilter(tickets: ListBuffer[Ticket]): ListBuffer[Ticket] = {
    tickets.filter(_.discount.discountValue >= discount)
  }
}