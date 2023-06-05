package Users

import Ticket.Ticket
import Utility.Discount
import Utility.Discount.FULL

import java.io.File
import java.util.UUID

class User(val name: String,
           val surname: String,
           val age: Int,
           val discount: Discount = FULL,
           var userFolderUrl: String = "",
           var points: Int = 0,
           var actualTickets: List[Ticket] = List[Ticket]()) {


  require(name.nonEmpty, "Name must not be empty")
  require(surname.nonEmpty, "Surname must not be empty")
  require(age > 0, "Age must be greater than 0")

  if (userFolderUrl.isEmpty) {
    val uniqueFolderName = s"user_${UUID.randomUUID()}"
    val defaultFolderPath = s"/Data/UsersFolders/$uniqueFolderName/"

    val userFolder = new File(defaultFolderPath)
    if (!userFolder.exists()) {
      userFolder.mkdirs()
    }
    userFolderUrl = defaultFolderPath
  }

  override def toString: String = {
    s"Users.User(name=$name, surname=$surname, age=$age, discount=$discount, userFolderUrl=$userFolderUrl, points=$points, actualTickets=$actualTickets)"
  }
}

