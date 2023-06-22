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

import scala.collection.mutable.ListBuffer

class StorageSpec extends AnyFlatSpec with Matchers {
  "updateStorage" should "remove invalid tickets from TicketsAvailable" in {
    val user = new User("name", "surname", 18)
    val ticket1 = new Ticket(Discount.SENIOR, 1, new dateStruct(0, 0, 0), new dateStruct(10, 0, 0), "123", "City1", user, waitingRoomEnum.P1.getPath("P1"))
    val ticket2 = new Ticket(Discount.STUDENT, 2, new dateStruct(11, 0, 0), new dateStruct(10, 0, 0), "456", "City2", user, waitingRoomEnum.P1.getPath("P2")
    )
    val ticket3 = new Ticket(Discount.FULL, 3, new dateStruct(12, 0, 0), new dateStruct(10, 0, 0), "789", "City1", user, waitingRoomEnum.P1.getPath("P3"))
    val storage = new Storage(ListBuffer(ticket1, ticket2, ticket3))

    println("Przed aktualizacją:")
    println(storage.TicketsAvialiable.mkString(", "))

    storage.updateStorage()

    println("Po aktualizacji:")
    println(storage.TicketsAvialiable.mkString(", "))

//    storage.TicketsAvialiable should contain only(ticket1,ticket2, ticket3)
  }

  //  "filterTickets" should "apply filters and return filtered tickets" in {
  //    val ticket1 = Ticket(dateStruct = Some(DateStruct(...)), routeNumber = Some("123"), location = Some("City1"), discount = Some(Discount(...)))
  //    val ticket2 = Ticket(dateStruct = Some(DateStruct(...)), routeNumber = Some("456"), location = Some("City2"), discount = Some(Discount(...)))
  //    val ticket3 = Ticket(dateStruct = Some(DateStruct(...)), routeNumber = Some("789"), location = Some("City1"), discount = Some(Discount(...)))
  //    val storage = new Storage(ListBuffer(ticket1, ticket2, ticket3))
  //
  //    val filteredTickets = storage.filterTickets(routeNumber = Some("123"), location = Some("City1"))
  //
  //    filteredTickets should contain only ticket1
  //  }

  //  "transferTicket" should "transfer a ticket from user1 to user2" in {
  //    val ticket = Ticket(ticketURL = "user1/ticket1.pdf")
  //    val user1 = User("John", "Doe", ListBuffer(ticket))
  //    val user2 = User("Jane", "Smith", ListBuffer())
  //    val storage = new Storage()
  //
  //    storage.transferTicket(user1, user2, ticket)
  //
  //    user1.userTickets should not contain ticket
  //    user2.userTickets should contain only ticket
  //    ticket.ticketURL should be ("user2/ticket1.pdf")
  //  }
}
