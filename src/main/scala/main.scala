import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine
import Users.User
import dataManager.Storage
import Ticket.*
import Utility.dateStruct

def parseDateStruct(dateString: String): dateStruct = {
  val parts = dateString.split(":")
  val hour = parts(0).toInt
  val minute = parts(1).toInt
  val seconds = parts(2).toInt
  new dateStruct(hour, minute, seconds)
}
@main
def main(): Unit = {
  val users = ListBuffer[User]() //FIXME: czy nie lepiej miec tyhc userow w storage?
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
        //FIXME: nwm czy sie tak da ale można jakoś zorbić żeby dało radę podać kilka filtrów
        // tych samych, np żęby filtorwało różne zniżki naraz, w sensie, i student i full (zobacz storage na dole).
        // Dodatkowo można przekazać pustą wartość do tego filtra i wtedy się przeszuka tylko
        // po jednym filtrze. A jak sie nie da to tak jak jest tez jest git chyba
        // required time powinno byc chyba dateStruct, zorbilem jakis parser jak cos
        // dodalem w enumie parser zeby mozna discout  podwac  jako Full czy słownie jakos

        var matchedTickets = storage.filterTickets(Some(parseDateStruct(reqTime)),Some(routeNr), None,Some(reqUser.discount))
        if(matchedTickets.isEmpty)println("NOT FOUND MATCHING TICKETS")
        else{
          var ticket = matchedTickets.head
          storage.transferTicket(reqUser,ticket.user,ticket) //FIXME: tu chyba na odwrut
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