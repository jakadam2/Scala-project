package dataManager

import java.io.File

object TicketTransferer {
  def transfer(source:String,dest:String) = {
    val sourceFile = File(source)
    val destFile = File(dest + "/" + source.split("/").last)
    sourceFile.renameTo(destFile)
  }
}
