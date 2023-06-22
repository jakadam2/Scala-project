package Users

import Ticket.Ticket
import Utility.Discount
import Utility.Discount.FULL
import java.io.File
import java.util.UUID
import scala.collection.mutable.ListBuffer
import java.io.{File, FileOutputStream,FileInputStream}
import java.nio.channels.Channels
import java.nio.file.{Files, Paths}
import dataManager.TicketTransferer.transfer

case class User(val name: String,
           val surname: String,
           val age: Int,
           val city:String="",
           val discount: Discount = FULL,
           var userFolderUrl: String = "",
           var points: Int = 0,
           var userTickets: ListBuffer[Ticket] = ListBuffer[Ticket]()) {


  require(name.nonEmpty, "Name must not be empty")
  require(surname.nonEmpty, "Surname must not be empty")
  require(age > 0, "Age must be greater than 0")
  var amountOfPoints = 0

  if (userFolderUrl.isEmpty) {
    val uniqueFolderName = s"user_${UUID.randomUUID()}"
    val defaultFolderPath = s"./Data/UsersFolders/$uniqueFolderName/"

    val userFolder = File(defaultFolderPath)
    if (!userFolder.exists()) {
      userFolder.mkdirs()
    }
    userFolderUrl = defaultFolderPath
  }
  def addTicket(ticket: Ticket): Unit={

    transfer(ticket.ticketURL,userFolderUrl)
    ticket.ticketURL = userFolderUrl + ticket.ticketFileName
    userTickets += ticket
  }

  def incPoints(): Unit= {
    amountOfPoints += 1
  }

  def decPoints(): Unit = {
    if (amountOfPoints > 0){
    amountOfPoints -= 1}
  }
  override def toString: String = {
    s"Users.User(name=$name, surname=$surname, age=$age, discount=$discount, userFolderUrl=$userFolderUrl, points=$points, actualTickets=$userTickets)"
  }
}

