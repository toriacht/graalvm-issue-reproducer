package org.acme.quickstart;


import org.acme.quickstart.com.me.hypno.razor.main.Scheduler;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/count")
public class SchedulerResource {

    @Inject
    Scheduler counter;            //


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello : " ;
    }
}
