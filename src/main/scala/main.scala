import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine
import Users.User
@main
def main(): Unit = {
  val users = ListBuffer[User]()
  while (true){
    var command = readLine()
    var splitedCommand = command.split(" ")
    splitedCommand match {
      case Array("add",name,surname,age) => {
        println("USER ADDED")
        println("NAME: ".concat(name))
        println("SURNAME: ".concat(surname))
        println("AGE: ".concat(age))
        users += User(name,surname,age.toInt)
      }
      case _ => println("something else")
    }



  }
}