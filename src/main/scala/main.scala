import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine
import Users.User
import dataManager.Storage
import Ticket.*
import Utility.dateStruct
import Utility.waitingRoomEnum

def parseDateStruct(dateString: String): dateStruct = {
  val parts = dateString.split(":")
  val hour = parts(0).toInt
  val minute = parts(1).toInt
  val seconds = parts(2).toInt
  new dateStruct(hour, minute, seconds)
}


@main
def main(): Unit = {

  val users = ListBuffer[User]()
  def findUser(name:String,surname:String) = {
    var maybeReqUser = users.find(u => {
      u.name == name && u.surname == surname
    })
    maybeReqUser
  }

  val storage = Storage()
  while (true){
    var command = readLine()
    var splitedCommand = command.split(" ")

    splitedCommand match {

      case Array("add","user",name,surname,age) => {
        println("USER ADDED")
        println("NAME: ".concat(name))
        println("SURNAME: ".concat(surname))
        println("AGE: ".concat(age))
        users += User(name,surname,age.toInt)
      }

      case Array("take","ticket",name,surname,routeNr,reqTime) =>{
        val maybeReqUser = findUser(name, surname)
        maybeReqUser match {

          case Some(user) => {
            var matchedTickets = storage.filterTickets(Some(parseDateStruct(reqTime)), Some(routeNr), None, Some(user.discount))
            if (matchedTickets.isEmpty) println("NOT FOUND MATCHING TICKETS")
            else {
              user.decPoints()
              val ticket = matchedTickets.head
              storage.transferTicket(ticket.user, user, ticket)
            }
          }
          case None => println("USER NOT EXIST")

        }
      }

      case Array("give","ticket",name,surname,routeNr,city) =>{
        val maybeReqUser = findUser(name,surname)
        maybeReqUser match {

          case Some(user) => {
            user.incPoints()
            val givenTicket = Ticket(user,city,routeNr)
            println("TICKET ADDED")
          }

          case None => println("USER NOT EXIST")

        }
      }

      case Array("check","points",name,surname) => {
        val maybeUser = findUser(name, surname)
        maybeUser match{
          case Some(user) => println("USER POINTS: ".concat(user.points.toString))
          case None => println("USER NOT EXIST")
        }
      }

      case _ => println("something else")
    }

    storage.updateStorage()
  }
}
