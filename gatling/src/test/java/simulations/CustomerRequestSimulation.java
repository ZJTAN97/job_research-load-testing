package simulations;

import java.time.Duration;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class CustomerRequestSimulation extends Simulation {

    // shared base configuration of all upcoming HTTP requests
    HttpProtocolBuilder httpProtocol = HttpDsl.http
            .baseUrl("http://localhost:8080")
            .acceptHeader("application/json")
            .userAgentHeader("Gatling/Performance Test");


    FeederBuilder<String> feeder = csv("customer.csv").random();


    // GET
    ChainBuilder get = exec(
            http("get-customer-request")
                    .get("/api/customers")
                    .check(status().is(200)));

    // POST
    ChainBuilder post = feed(feeder)
            .exec(
                    http("create-customer-request")
                            .post("/api/customers")
                            .header("Content-Type", "application/json")
                            .body(StringBody(
                                    "{ \"name\": \"#{name}\", \"age\": \"#{age}\", \"bio\": \"#{bio}\" }"
                            ))
                            .check(status().is(201)));

    ScenarioBuilder scn = scenario("Load Test Creating and Getting customers").exec(get, post);
    ScenarioBuilder scn2 = scenario("Load Test Creating and Getting customers with ramp up effect").exec(get, post);


    {
        setUp(
                scn.injectOpen(constantUsersPerSec(2).during(Duration.ofSeconds(35))),
                scn2.injectOpen(rampUsers(2).during(10))
        ).protocols(httpProtocol);
    }

}
