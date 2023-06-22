import org.scalatest.*
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.{convertToStringShouldWrapperForVerb, should, shouldEqual}
import flatspec.AnyFlatSpec
import Ticket.Ticket
import Users.User
import Utility.dateStruct
import Utility.Discount
import Utility.waitingRoomEnum
import dataManager.{Storage, TicketTransferer}

import java.io.{File, PrintWriter}
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import java.util.Comparator
import java.nio.file.Path


class TicketTrannsfererSpec extends AnyFlatSpec with Matchers {

  "TicketTransferer" should "transfer file to the correct destination folder" in {
    val sourcePath = "./srcD/srcFile.txt"
    val destPath = "./dest"

    createDirectory("srcD")
    createDirectory("dest")
    createFile(sourcePath)

    TicketTransferer.transfer(sourcePath, destPath)

    val expectedDestFile = new File(destPath + "/srcFile.txt")
    expectedDestFile.exists() shouldEqual true

    deleteFile(sourcePath)
    deleteFile(destPath + "/srcFile.txt")
    deleteDirectory("srcD")
    deleteDirectory("dest")
  }

  it should "keep the source file after transfer" in {
    val sourcePath = "./srcD/srcFile.txt"
    val destPath = "./dest"

    createDirectory("srcD")
    createDirectory("dest")
    createFile(sourcePath)

    TicketTransferer.transfer(sourcePath, destPath)

    val sourceFile = new File(sourcePath)
    sourceFile.exists() shouldEqual false

    deleteFile(destPath + "/srcFile.txt")
    deleteDirectory("srcD")
    deleteDirectory("dest")
  }


  def createFile(filePath: String): Unit = {
    val file = new File(filePath)
    val parentDir = file.getParentFile
    if (parentDir != null) {
      parentDir.mkdirs()
    }
    val writer = new PrintWriter(file)
    writer.write("Testowy plik")
    writer.close()
  }

  def deleteFile(filePath: String): Unit = {
    val file = new File(filePath)
    if (file.exists()) {
      file.delete()
    }
  }

  def createDirectory(dirPath: String): Unit = {
    Files.createDirectories(Paths.get(dirPath))
  }

  def deleteDirectory(dirPath: String): Unit = {
    val path: Path = Paths.get(dirPath)
    if (Files.exists(path)) {
      Files.walk(path)
        .sorted(java.util.Comparator.reverseOrder())
        .map[File](_.toFile)
        .forEach(_.delete())
    }
  }
}
