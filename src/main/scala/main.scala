import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine
import Users.User
import dataManager.Storage
import Ticket.*
@main
def main(): Unit = {
  val users = ListBuffer[User]()
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
        var userExist = true
        var maybeReqUser = users.find(u => {u.name == name && u.surname == surname})
        var reqUser:User =  maybeReqUser match {
          case Some(user) => user
          case None => {
            println("USER NOT EXIST")
            userExist = false
            User("Noone","Noone",6)
          }
        }
        var routeNrFilter = RouteNumberFilter(routeNr)
        var timeFilter = TimeLeftFilter(reqTime.toInt)
        var discountFilter = DiscountFilter(reqUser.discount.discountValue)
        var finalFilter = CompositeFilter(List(routeNrFilter,timeFilter,discountFilter))
        var matchedTickets = storage.filterTickets(finalFilter)
        if(matchedTickets.isEmpty)println("NOT FOUND MATCHING TICKETS")
        else{
          var ticket = matchedTickets.head
          storage.transferTicket(reqUser,ticket.user,ticket,ticket.user.userFolderUrl)
        }
      }

      case Array("give","ticket",name,surname) =>{
        var userExist = true
        var maybeReqUser = users.find(u => {
          u.name == name && u.surname == surname
        })
        var reqUser: User = maybeReqUser match {
          case Some(user) => user
          case None => {
            println("USER NOT EXIST")
            userExist = false
            User("Noone", "Noone", 6)
          }
        }
        if(userExist){
          //tu wlasnie chb trzeba przladowac z jakiej przechowyalni do folderu uzytkownika
        }
      }

      case _ => println("something else")
    }

    storage.updateStorage()
  }
}