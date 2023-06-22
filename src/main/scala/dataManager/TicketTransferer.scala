package dataManager

import java.io.File

object TicketTransferer {
  def transfer(source: String, dest: String): Unit = {
    val sourceFile = new File(source)

    val destFile = new File(dest + "/" + source.split("/").last)
    sourceFile.renameTo(destFile)
  }
}