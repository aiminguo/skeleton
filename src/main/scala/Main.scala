
import play.api.libs.json.{Json, Reads}

import java.util.logging.Logger

object DemoApp {
  val url = getClass.getResource("logging.properties")
  System.setProperty("java.util.logging.config.file", url.getPath)
  val logger = Logger.getLogger(getClass.getName)
  case class Person(id: Int, name: String)
  object Person {
    implicit val personReads: Reads[Person] = Json.reads[Person]
    def fromJson(input: String): List[Person] = {
      val jsValue = Json.parse(input)
      Json.fromJson[List[Person]](jsValue).get
    }
  }
  def main(args: Array[String]): Unit = {
    logger.info("Hello world!")
    val input =
      """[
        | { "id": 1, "name": "test1"},
        | { "id": 2, "name": "test2"}
        |]
        |""".stripMargin
    val persons = Person.fromJson(input)
    logger.info(s"$persons")
  }
}
