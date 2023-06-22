import org.scalatest._
import org.scalatest.matchers.should.Matchers
import Ticket._
import Utility.dateStruct
import Utility.Discount
import scala.collection.mutable.ListBuffer
import org.scalatest.*
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.{convertToStringShouldWrapperForVerb, should, shouldEqual}
import flatspec.AnyFlatSpec

import Users.User
import Utility.dateStruct
import Utility.Discount
import Utility.waitingRoomEnum
import dataManager.{Storage, TicketTransferer}

class TicketFilterSpec extends AnyFlatSpec  {
  var path="";
  "CityFilter" should "filter tickets by city" in {
    val tickets = ListBuffer(
      new Ticket(Discount.FULL, 1, dateStruct(0,0,0), dateStruct(0,0,0), "123", "New York", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 2, dateStruct(0,0,0), dateStruct(0,0,0), "456", "London", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 3, dateStruct(0,0,0), dateStruct(0,0,0), "789", "Paris", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 4, dateStruct(0,0,0), dateStruct(0,0,0), "012", "New York", new User("name", "surname", 18),  path)
    )
    val cityFilter = new CityFilter("New York")
    val filteredTickets = cityFilter.applyFilter(tickets)
    filteredTickets.size shouldEqual 2
    filteredTickets.forall(_.city == "New York") shouldEqual true
  }

  "CompositeFilter" should "apply multiple filters to tickets" in {
    val tickets = ListBuffer(
      new Ticket(Discount.FULL, 1, dateStruct(0,0,0), dateStruct(0,0,0), "123", "New York", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 2, dateStruct(0,0,0), dateStruct(0,0,0), "456", "London", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 3, dateStruct(0,0,0), dateStruct(0,0,0), "789", "Paris", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 4, dateStruct(0,0,0), dateStruct(0,0,0), "012", "New York", new User("name", "surname", 18),  path)
    )
    val filters = ListBuffer(new CityFilter("New York"), new DiscountFilter(0.00))
    val compositeFilter = new CompositeFilter(filters)
    val filteredTickets = compositeFilter.applyFilter(tickets)
    filteredTickets.size shouldEqual 2
    filteredTickets.head.city shouldEqual "New York"
    filteredTickets.head.discount shouldEqual Discount.FULL
  }

  "DiscountFilter" should "filter tickets by discount value" in {
    val tickets = ListBuffer(
      new Ticket(Discount.FULL, 1, dateStruct(0,0,0), dateStruct(0,0,0), "123", "New York", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 2, dateStruct(0,0,0), dateStruct(0,0,0), "456", "London", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 3, dateStruct(0,0,0), dateStruct(0,0,0), "789", "Paris", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 4, dateStruct(0,0,0), dateStruct(0,0,0), "012", "New York", new User("name", "surname", 18),  path)
    )
    val discountFilter = new DiscountFilter(0.00)
    val filteredTickets = discountFilter.applyFilter(tickets)
    filteredTickets.size shouldEqual 4
    filteredTickets.forall(_.discount == Discount.FULL) shouldEqual true
  }

  "RouteNumberFilter" should "filter tickets by route number" in {
    val tickets = ListBuffer(
      new Ticket(Discount.FULL, 1, dateStruct(0,0,0), dateStruct(0,0,0), "123", "New York", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 2, dateStruct(0,0,0), dateStruct(0,0,0), "456", "London", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 3, dateStruct(0,0,0), dateStruct(0,0,0), "789", "Paris", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 4, dateStruct(0,0,0), dateStruct(0,0,0), "012", "New York", new User("name", "surname", 18),  path)
    )
    val routeNumberFilter = new RouteNumberFilter("123")
    val filteredTickets = routeNumberFilter.applyFilter(tickets)
    filteredTickets.size shouldEqual 1
    filteredTickets.head.routeNumber shouldEqual "123"
  }

  "TimeLeftFilter" should "filter tickets by time left" in {
    val tickets = ListBuffer(
      new Ticket(Discount.FULL, 1, dateStruct(1,0,0), dateStruct(0,0,0), "123", "New York", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 2, dateStruct(2,0,0), dateStruct(0,0,0), "456", "London", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 3, dateStruct(3,0,0), dateStruct(0,0,0), "789", "Paris", new User("name", "surname", 18),  path),
      new Ticket(Discount.FULL, 4, dateStruct(4,0,0), dateStruct(0,0,0), "012", "New York", new User("name", "surname", 18),  path)
    )
    val timeLeftFilter = new TimeLeftFilter(7200) // 2 hours
    val filteredTickets = timeLeftFilter.applyFilter(tickets)
    filteredTickets.size shouldEqual 4
    filteredTickets.forall(ticket => {
      val timeLeftInSeconds = ticket.timeLeft().hour * 3600 + ticket.timeLeft().minute * 60 + ticket.timeLeft().seconds
      timeLeftInSeconds <= 7200
    }) shouldEqual true
  }
}