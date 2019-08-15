package org.acme.quickstart;


import org.acme.quickstart.db.Person;
import org.acme.quickstart.db.Status;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.jboss.resteasy.util.HttpHeaderNames.USER_AGENT;

//import static org.apache.http.HttpHeaders.USER_AGENT;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService service;

    @PostConstruct
    void testOutsideAccess(){
        try {

        String url = "http://www.google.com/";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET

            con.setRequestMethod("GET");


        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(@PathParam("name") String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<String> hello() {
        return CompletableFuture.supplyAsync(() -> {
            return "hello";
        });
    }


    @Transactional
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/persist/{name}")
    public String persist(@PathParam("name") String name) {

//        PersistService persistService = new PersistService();
        Person person ;

        // creating a person
        person = new Person();
        person.name = name;
        person.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        person.status = Status.Alive;
        person.persist();
        return service.greeting(String.valueOf(person.id));
    }


    @Transactional
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/persistTestTrades")
    public String persistTestTrades(String name) {
        return null;

//
//        CryptoTrade cryp1 = new CryptoTrade();
//        cryp1.direction
//
//        CryptoTrade cryp2 = new CryptoTrade("2-d", null, TradeStatusEnum.OPEN, StrategyDirectionEnum.BEAR, 2);
//        CryptoTrade cryp3 = new CryptoTrade("3-o", null, TradeStatusEnum.CLOSED, StrategyDirectionEnum.BULL, 2);
//        CryptoTrade cryp4 = new CryptoTrade("4-d-1", null, TradeStatusEnum.CLOSED, StrategyDirectionEnum.BULL, 2);
//        CryptoTrade cryp5 = new CryptoTrade("5-c-c-c", null, TradeStatusEnum.CLOSED, StrategyDirectionEnum.BEAR, 2);
//        CryptoTrade cryp6 = new CryptoTrade("6-v-b-n", null, TradeStatusEnum.CLOSED, StrategyDirectionEnum.BULL, 2);
//
//        return service.greeting(String.valueOf(person.id));
    }






//
//
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String hello() {
//        return "hello";
//    }
}