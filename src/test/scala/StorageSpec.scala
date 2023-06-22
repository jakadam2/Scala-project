import Ticket.Ticket
import Users.User
import Utility.dateStruct
import Utility.Discount
import Utility.waitingRoomEnum
import dataManager.Storage
import org.scalatest.*
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.{convertToStringShouldWrapperForVerb, should}
import flatspec.AnyFlatSpec
import java.util.Calendar

import scala.collection.mutable.ListBuffer

class StorageSpec extends AnyFlatSpec with Matchers {
  "updateStorage" should "remove invalid tickets from TicketsAvailable" in {
    val user = new User("name", "surname", 18)
    val now = Calendar.getInstance()
    val nowStruct = dateStruct(now.get(Calendar.PM), now.get(Calendar.MINUTE), now.get(Calendar.SECOND))
    val validDateTime = nowStruct.subtract(new dateStruct(1, 59, 59))
    val expiredDateTime = nowStruct.subtract(new dateStruct(9999999, 999, 9999))
    val ticket1 = new Ticket(Discount.SENIOR, 1, nowStruct, validDateTime, "123", "City1", user, "")
    val ticket2 = new Ticket(Discount.STUDENT, 2, nowStruct, expiredDateTime, "456", "City2", user, ""
    )
    val ticket3 = new Ticket(Discount.FULL, 3, nowStruct, validDateTime, "789", "City1", user, "")
    val storage = new Storage()
    storage.addTicket(ticket1)
    storage.addTicket(ticket2)
    storage.addTicket(ticket3)
    storage.updateStorage()
    storage.TicketsAvialiable should contain only(ticket1, ticket3)
  }

    "filterTickets" should "apply filters and return filtered tickets" in {
      val now = Calendar.getInstance()
      val user = new User("name", "surname", 18)
      val nowStruct = dateStruct(now.get(Calendar.PM), now.get(Calendar.MINUTE), now.get(Calendar.SECOND))
      val validDateTime = nowStruct.subtract(new dateStruct(1, 59, 59))
      val ticket1 = new Ticket(Discount.SENIOR, 1, nowStruct, validDateTime, routeNumber = ("123"),  ("City1"),  user, "")
      val ticket2 = new Ticket(Discount.SENIOR, 1, nowStruct, validDateTime, routeNumber = ("456"),  ("City2"),  user, "")
      val ticket3 = new Ticket(Discount.SENIOR, 1, nowStruct, validDateTime, routeNumber = ("789"),  ("City1"),  user, "")
      val storage = new Storage(ListBuffer(ticket1, ticket2, ticket3))

      val filteredTickets = storage.filterTickets(routeNumber = Some("123"), location = Some("City1"))

      filteredTickets should contain only ticket1
    }

}
