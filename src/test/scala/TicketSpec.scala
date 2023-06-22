import org.scalatest.*
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
import java.util.Calendar

class TicketSpec extends AnyFlatSpec with Matchers {
  "isValid" should "return true when the ticket is still valid" in {
    val user = new User("name", "surname", 18)
    val now = Calendar.getInstance()
    val nowStruct = dateStruct(now.get(Calendar.PM), now.get(Calendar.MINUTE), now.get(Calendar.SECOND))
    val validDateTime = nowStruct.subtract(new dateStruct(1, 59, 59))
    val ticket = new Ticket(Discount.SENIOR, 1, nowStruct, validDateTime, "123", "City1", user, "")
    val isValid = ticket.isValid()

    isValid should be(true)
  }

  it should "return false when the ticket has expired" in {
    val user = new User("name", "surname", 18)
    val now = Calendar.getInstance()
    val nowStruct = dateStruct(now.get(Calendar.PM), now.get(Calendar.MINUTE), now.get(Calendar.SECOND))
    val expiredDateTime = nowStruct.subtract(new dateStruct(9999999, 999, 9999))
    val ticket = new Ticket(Discount.SENIOR, 1,  nowStruct,expiredDateTime, "123", "City1", user, "")
    val isValid = ticket.isValid()

    isValid should be(false)
  }

  it should "return true when the ticket has just expired" in {
    val user = new User("name", "surname", 18)
    val now = Calendar.getInstance()
    val nowStruct = dateStruct(now.get(Calendar.PM), now.get(Calendar.MINUTE), now.get(Calendar.SECOND))
    val expiredDateTime = nowStruct.subtract(new dateStruct(0, 1, 0))
    val ticket = new Ticket(Discount.SENIOR, 1, nowStruct, expiredDateTime, "123", "City1", user, "")
    val isValid = ticket.isValid()

    isValid should be(true)
  }

  it should "return the correct ticket number" in {
    val user = new User("name", "surname", 18)
    val now = Calendar.getInstance()
    val nowStruct = dateStruct(now.get(Calendar.PM), now.get(Calendar.MINUTE), now.get(Calendar.SECOND))
    val validDateTime = nowStruct.subtract(new dateStruct(-1, -59, -59))
    val ticketNumber = "123"
    val ticket = new Ticket(Discount.FULL, 1, nowStruct, validDateTime, ticketNumber, "City1", user, "")
    val returnedTicketNumber = ticket.routeNumber;

    returnedTicketNumber should be(ticketNumber)
  }

  }
