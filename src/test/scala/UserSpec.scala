import org.scalatest._
import org.scalatest.*
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.{convertToStringShouldWrapperForVerb, should, shouldEqual}
import flatspec.AnyFlatSpec
import Ticket.Ticket
import Users.User
import Utility.dateStruct
import Utility.Discount
import Utility.waitingRoomEnum
import dataManager.{Storage, TicketTransferer}

import java.io.{File, PrintWriter}
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import java.util.Comparator
import java.nio.file.Path


class UserSpec extends AnyFlatSpec with Matchers  {
  "A User" should "throw an exception when name is empty" in {
    assertThrows[IllegalArgumentException] {
      User("", "Smith", 30)
    }
  }

  it should "throw an exception when surname is empty" in {
    assertThrows[IllegalArgumentException] {
      User("John", "", 30)
    }
  }

  it should "throw an exception when age is not greater than 0" in {
    assertThrows[IllegalArgumentException] {
      User("John", "Smith", 0)
    }
  }

  it should "create a user folder with a unique name if userFolderUrl is empty" in {
    val user = User("John", "Smith", 30)
    assert(user.userFolderUrl.nonEmpty)
    assert(user.userFolderUrl.startsWith("./Data/UsersFolders/user_"))
    assert(user.userFolderUrl.endsWith("/"))
  }

  it should "increase the points count by 1 when incPoints is called" in {
    val user = User("John", "Smith", 30)
    user.incPoints()
    assert(user.amountOfPoints == 1)
  }

  it should "decrease the points count by 1 when decPoints is called" in {
    val user = User("John", "Smith", 30)
    user.amountOfPoints = 2
    user.decPoints()
    assert(user.amountOfPoints == 1)
  }

  it should "not decrease the points count below 0 when decPoints is called" in {
    val user = User("John", "Smith", 30)
    user.amountOfPoints = 0
    user.decPoints()
    assert(user.amountOfPoints == 0)
  }
}
