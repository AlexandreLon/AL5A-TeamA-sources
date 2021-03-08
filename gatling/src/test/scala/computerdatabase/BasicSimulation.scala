package computerdatabase

import io.gatling.core.Predef._
import io.gatling.core.session
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val BASE_URL = "http://localhost:3000/api/"

  val httpProtocol = http
    .baseUrl(BASE_URL) // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml,application/json,text/plain;q=0.9,*/*;q=0.8") // Here are the common headers
  //    .acceptEncodingHeader("gzip, deflate")
  //    .acceptLanguageHeader("en-US,en;q=0.5")
  //    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  //  exec(http("create_maintenance")
  //    .post("maintenance")
  //    .body(RawFileBody("json/maintenanceCreation.json")).asJson)
  //    .exec(http("create_mishap")
  //      .post("mishap")
  //      .body(RawFileBody("json/mishapCreation.json")).asJson)
  //    .exec(http("create_supplier_1")
  //      .post("supplier")
  //      .check(jsonPath("$.id").saveAs("supplier1Id"))
  //      .body(RawFileBody("json/supplierCreation.json")).asJson)
  //    // create 3 suppliers
  //    .exec(http("create_supplier_2")
  //      .post("supplier")
  //      .check(jsonPath("$.id").saveAs("supplier2Id"))
  //      .body(RawFileBody("json/supplierCreation.json")).asJson)
  //    .pause(1)
  //    .exec(http("create_supplier_3")
  //      .post("supplier")
  //      .check(jsonPath("$.id").saveAs("supplier3Id"))
  //      .body(RawFileBody("json/supplierCreation.json")).asJson)
  //    .pause(1)

  val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    // create 3 offers with the suppliers
    .exec(
      http("get bids")
        .get("supplier/" + 1 + "/bids"))
    .pause(1)
    .exec(
      http("make_offer")
        .post("supplier/" + 82 + "/outbid")
        .check(jsonPath("$.id").saveAs("offerId"))
        .body(StringBody("""{ "supplierId": """" + 1 + """", "price": 900, "proposedDate": "2021-02-11T00:00:00.000Z" }""")).asJson)

    .exec(
      http("get bids")
        .get("supplier/" + 2 + "/bids"))
    .pause(1)
    .exec(
      http("make_offer")
        .post("supplier/" + 82 + "/outbid")
        .check(jsonPath("$.id").saveAs("offerId"))
        .body(StringBody("""{ "supplierId": """" + 2 + """", "price": 1000, "proposedDate": "2021-02-11T00:00:00.000Z" }""")).asJson)

    .exec(
      http("get bids")
        .get("supplier/" + 3 + "/bids"))
    .pause(1)
    .exec(
      http("make_offer")
        .post("supplier/" + 82 + "/outbid")
        .check(jsonPath("$.id").saveAs("offerId"))
        .body(StringBody("""{ "supplierId": """" + 3 + """", "price": 2300, "proposedDate": "2021-02-11T00:00:00.000Z" }""")).asJson)


  //accept an offer
  //    .exec(
  //      http("accept_offer")
  //        .put("bid/${offerId}/accept"))


  //      .post("supplier/" + 34 + "/outbid")
  //      .body(RawFileBody("json/offerCreation.json")).asJson)


  //      .pause(7) // Note that Gatling has recorder real time pauses
  //      .exec(http("request_2")
  //        .get("/computers?f=macbook"))
  //      .pause(2)
  //      .exec(http("request_3")
  //        .get("/computers/6"))
  //      .pause(3)
  //      .exec(http("request_4")
  //        .get("/"))
  //      .pause(2)
  //      .exec(http("request_5")
  //        .get("/computers?p=1"))
  //      .pause(670.milliseconds)
  //      .exec(http("request_6")
  //        .get("/computers?p=2"))
  //      .pause(629.milliseconds)
  //      .exec(http("request_7")
  //        .get("/computers?p=3"))
  //      .pause(734.milliseconds)
  //      .exec(http("request_8")
  //        .get("/computers?p=4"))
  //      .pause(5)
  //      .exec(http("request_9")
  //        .get("/computers/new"))
  //      .pause(1)
  //      .exec(http("request_10") // Here's an example of a POST request
  //        .post("/computers")
  //        .formParam("""name""", """Beautiful Computer""") // Note the triple double quotes: used in Scala for protecting a whole chain of characters (no need for backslash)
  //        .formParam("""introduced""", """2012-05-30""")
  //        .formParam("""discontinued""", """""")
  //        .formParam("""company""", """37"""))

  setUp(scn.inject(
//    atOnceUsers(1000),
//    rampUsers(10000).during(60.seconds),
    constantUsersPerSec(1000).during(30.seconds).randomized,
  ).protocols(httpProtocol))

  //  setUp(scn.inject(
  //    rampUsers(10).during(5.seconds),
  //  ).protocols(httpProtocol))
  //
  //  setUp(scn.inject(
  //    constantUsersPerSec(5).during(20.seconds).randomized
  //  ).protocols(httpProtocol))
}
