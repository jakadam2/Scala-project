package dataManager


import Ticket.{Ticket, TicketFilter,TimeLeftFilter,RouteNumberFilter,CityFilter,DiscountFilter,CompositeFilter}
import Users.User
import Utility.Discount
import jdk.jshell.spi.ExecutionControl.NotImplementedException
import Utility.dateStruct
import dataManager.TicketTransferer.transfer
import java.io.{File, FileOutputStream,FileInputStream}
import java.nio.channels.Channels
import java.nio.file.{Files, Paths}
import scala.collection.mutable.ListBuffer

class Storage (var TicketsAvialiable: ListBuffer[Ticket] = ListBuffer[Ticket]()){ //zmieniłem liste na listbuffer bo go da się modyfikować ale jak cos to tu moze byc co innego  

  val waitingRoomPath = "./Data/WaitingRoom"
  val waitingRoomFile = File(waitingRoomPath)
  if(!waitingRoomFile.exists()){
    waitingRoomFile.mkdirs()
  }


  def updateStorage(): Unit = { //TODO: zbiera wszystkie bilety z uzytkownikow
    
    TicketsAvialiable = TicketsAvialiable.filter(_.isValid())
  }

  def filterTickets( //przykladowe wywoalnie nizej
                     dateStruct: Option[dateStruct] = None,
                     routeNumber: Option[String] = None,
                     location: Option[String] = None,
                     discount: Option[Discount] = None
                   ): ListBuffer[Ticket] = {
    updateStorage()
    val filters = ListBuffer[TicketFilter]()

    dateStruct.foreach { ds =>
      val timeLeftFilter = new TimeLeftFilter(ds.getmyVal())
      filters += timeLeftFilter
    }

    routeNumber.foreach { number =>
      val routeNumberFilter = new RouteNumberFilter(number)
      filters += routeNumberFilter
    }

    location.foreach { loc =>
      val cityFilter = new CityFilter(loc)
      filters += cityFilter
    }

    discount.foreach { disc =>
      val discountFilter = new DiscountFilter(disc.discountValue)
      filters += discountFilter
    }

    val compositeFilter = new CompositeFilter(filters)
    val filteredTickets = compositeFilter.applyFilter(TicketsAvialiable)
    filteredTickets
  }


  def transferTicket(user1: User, user2: User, ticket: Ticket): Unit = { //z user1 do user2 przkazuje ticket
    require(user1.userTickets.contains(ticket), user1.name +" " + user1.surname + " must possess the ticket.")
    // usuwa ticket z listy w user1
    //to powinno dzialac tak ze kopjuje z folderu usera1 do folderu usera2 plik o takiej nazwie co ticket.url
    //zmienia sciezke dostepu do ticketu 
    //dodaje do listy usera2 ten ticket
    
    user1.userTickets = user1.userTickets.filterNot(_ == ticket)
    transfer(ticket.ticketURL,user2.userFolderUrl)
    ticket.ticketURL = user2.userFolderUrl + ticket.ticketFileName
    user2.userTickets = user2.userTickets :+ ticket

  }



}

//wywoałnie przkładowego filtrowania:
//val date = DateStruct(hour = 12, minute = 0, seconds = 0)
//val routeNumber = "123"
//val location = "New York"
//val discounts = List(Discount.SENIOR, Discount.STUDENT)
//
//val filteredTickets = storage.filterTickets(
//  dateStruct = Some(date),
//  routeNumber = Some(routeNumber),
//  location = Some(location),
//  discount = Some(discounts)
//)
