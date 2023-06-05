package dataManager


import Ticket.{Ticket, TicketFilter}
import Users.User

import java.io.{File, FileOutputStream}
import java.nio.channels.Channels
import java.nio.file.{Files, Paths}

class Storage (var TicketsAvialiable: List[Ticket]){
  def updateStorage(): Unit = {
    TicketsAvialiable = TicketsAvialiable.filter(_.isValid())
  }

  def transferTicket(user1: User, user2: User, ticket: Ticket, copyURL: String): Unit = {
    require(user1.actualTickets.contains(ticket), user1.name +" " + user1.surname + " must possess the ticket.")

    // Remove the ticket from User1
    user1.actualTickets = user1.actualTickets.filterNot(_ == ticket)

    // Add the ticket to User2
    user2.actualTickets = user2.actualTickets :+ ticket

    // Create a copy of the ticket file at the specified URL
    val ticketFile = new File(ticket.ticketURL)
    val copyFile = new File(copyURL)

    val ticketChannel = new FileOutputStream(ticketFile).getChannel
    val copyChannel = new FileOutputStream(copyFile).getChannel

    ticketChannel.transferTo(0, ticketChannel.size(), copyChannel)

    ticketChannel.close()
    copyChannel.close()

    // Delete the original file
    Files.deleteIfExists(Paths.get(ticketFile.toURI))
  }



}

//val storage = new Storage(...)
//val timeLeftFilter = new TimeLeftFilter(60) // Filtruje bilety, którym pozostało mniej niż 60 sekund
//val routeNumberFilter = new RouteNumberFilter("123") // Filtruje bilety dla konkretnej trasy
//val cityFilter = new CityFilter("New York") // Filtruje bilety dla konkretnej lokalizacji
//val discountFilter = new DiscountFilter(0.2) // Filtruje bilety z określonym rabatem
//
//// Tworzenie kompozytowego filtru łączącego kilka filtrów
//val compositeFilter = new CompositeSearch(List(timeLeftFilter, routeNumberFilter, cityFilter, discountFilter))
//
//// Filtrowanie biletów za pomocą kompozytowego filtru
//val filteredTickets = storage.filterTickets(compositeFilter)
