package test1package

import io.gatling.http.Predef._
import io.gatling.core.Predef._

import Headers._

object CLP {





  val barclp = exec(http("CLP")

    .get(uri3 + "/FSX/${category}")
    .headers(headers_12))
    .pause(2)

  val lightclp =  exec(http("request_297")
    .get(uri3 + "/en_US/FSX/Light-Counter-Equipment/c/2000")
    .headers(headers_12))
    .pause(2)

}