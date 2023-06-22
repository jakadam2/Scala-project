import org.scalatest._
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.{convertToStringShouldWrapperForVerb, should, shouldEqual}
import flatspec.AnyFlatSpec
import Ticket.Ticket
import Users.User
import Utility.dateStruct
import Utility.Discount
import Utility.waitingRoomEnum
import dataManager.Storage

import java.time.LocalDateTime

class DateStructSpec extends AnyFlatSpec with Matchers {
  "subtract" should "return the correct difference between two dateStruct instances" in {
    val date1 = new dateStruct(10, 30, 45)
    val date2 = new dateStruct(5, 15, 10)
    val diff = date1.subtract(date2)
    diff.hour shouldEqual 5
    diff.minute shouldEqual 15
    diff.seconds shouldEqual 35
  }

  "getmyVal" should "return the correct value of myVal" in {
    val date = new dateStruct(2, 15, 30)
    date.getmyVal() shouldEqual 8130
  }

}
