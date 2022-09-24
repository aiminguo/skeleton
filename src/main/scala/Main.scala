import play.api.libs.json._

case class Car(make: String, model: String, cost: Int)
object Car {
  implicit val carReads: Reads[Car] = Json.reads[Car]
}
object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")

    val json =
      """
        |[
        |    {
        |       "make": "Tesla",
        |       "model": "Model S",
        |       "cost": 50000
        |    },
        |    {
        |       "make": "Tesla",
        |       "model": "Model D",
        |       "cost": 30000
        |    }
        |]
  """.stripMargin

    val parsedJsValue = Json.parse(json)
    val parsed  = Json.fromJson[List[Car]](parsedJsValue).get
    println(parsed)
  }
}