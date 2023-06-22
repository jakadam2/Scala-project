import org.scalatest._
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.{convertToStringShouldWrapperForVerb, should}
import flatspec.AnyFlatSpec
import Ticket.Ticket
import Users.User
import Utility.dateStruct
import Utility.Discount
import Utility.waitingRoomEnum
import dataManager.Storage

import java.time.LocalDateTime

class TicketSpec extends AnyFlatSpec with Matchers {
  "isValid" should "return true when the ticket is still valid" in {
    val user = new User("name", "surname", 18)
    val now = new dateStruct(0, 0, 0)
    val validDateTime = now.subtract(new dateStruct(-1, -59, -59))
    val ticket = new Ticket(Discount.SENIOR, 1, now, validDateTime, "123", "City1", user, waitingRoomEnum.P1.getPath("P1"))
    val isValid = ticket.isValid()

    isValid should be(true)
  }

  it should "return false when the ticket has expired" in {
    val user = new User("name", "surname", 18)
    val now = new dateStruct(0, 0, 0)
    val expiredDateTime = now.subtract(new dateStruct(1, 0, 0))
    val ticket = new Ticket(Discount.SENIOR, 1, now, expiredDateTime, "123", "City1", user, waitingRoomEnum.P1.getPath("P1"))
    val isValid = ticket.isValid()

    isValid should be(false)
  }

  it should "return true when the ticket has just expired" in {
    val user = new User("name", "surname", 18)
    val now = new dateStruct(0, 0, 0)
    val expiredDateTime = now.subtract(new dateStruct(0, 1, 0))
    val ticket = new Ticket(Discount.SENIOR, 1, now, expiredDateTime, "123", "City1", user, waitingRoomEnum.P1.getPath("P1"))
    val isValid = ticket.isValid()

    isValid should be(true)
  }

  it should "return the correct ticket number" in {
    val user = new User("name", "surname", 18)
    val now = new dateStruct(0, 0, 0)
    val validDateTime = now.subtract(new dateStruct(-1, -59, -59))
    val ticketNumber = "123"
    val ticket = new Ticket(Discount.FULL, 1, now, validDateTime, ticketNumber, "City1", user, waitingRoomEnum.P1.getPath("P1"))
    val returnedTicketNumber = ticket.nrOfTicket;

    returnedTicketNumber should be(ticketNumber)
  }

  "getValidityDuration" should "return the correct duration between two dateStruct objects" in {
    val user = new User("name", "surname", 18)
    val now = new dateStruct(0, 0, 0)
    val validDateTime = now.subtract(new dateStruct(-1, -30, -30))
    val ticket = new Ticket(Discount.FULL, 1, now, validDateTime, "123", "City1", user, waitingRoomEnum.P1.getPath("P1"))
    val validityDuration = ticket.timeLeft()

    validityDuration should be(new dateStruct(0, 30, 30))
  }

  "isValid" should "return false when the ticket has an invalid validity duration" in {
    val user = new User("name", "surname", 18)
    val now = new dateStruct(0, 0, 0)
    val expiredDateTime = now.subtract(new dateStruct(0, 30, 30))
    val ticket = new Ticket(Discount.FULL, 1, now, expiredDateTime, "123", "City1", user, waitingRoomEnum.P1.getPath("P1"))
    val isValid = ticket.isValid()

    isValid should be(false)
  }
}
