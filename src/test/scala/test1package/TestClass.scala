package test1package

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

import Login._
import CLP._




class TestClass extends Simulation {

  val httpProtocol = http
    .baseUrl("https://fonts.googleapis.com")
    .inferHtmlResources()
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:68.0) Gecko/20100101 Firefox/68.0")

  val profsxlistfeeder = csv("pro-fsxlist.csv").circular
  val credentialsfeeder = csv("Credentials.csv").circular


  before {
      exec(flushHttpCache)
      .exec(flushSessionCookies)
      .exec(flushCookieJar)

      }


  after {
      println("simulation is completed")
    }



  val scn1 = scenario("S1")
     .feed(profsxlistfeeder)
    .feed(credentialsfeeder)

       .group("USER LOGIN")
       {exec(Login.hp,Login.lp).pause(10,20)}

       .group("CATEGORY LISTING PAGE")
  {exec(CLP.barclp).pause(10,20)}


  setUp(scn1.inject(rampUsers(2) during (10 seconds))).protocols(httpProtocol)


}