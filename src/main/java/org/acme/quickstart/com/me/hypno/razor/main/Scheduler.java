package org.acme.quickstart.com.me.hypno.razor.main;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.acme.quickstart.com.me.hypno.razor.strategies.obvema.StrategyRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.quarkus.scheduler.Scheduled;

import java.util.concurrent.TimeUnit;

@ApplicationScoped              //
public class Scheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class.getSimpleName());


    @Inject
    StrategyRunner strategyRunner;

    @Transactional
    @ActivateRequestContext
    @Scheduled(cron="{cronjob.schedule}",delay = 1,delayUnit = TimeUnit.SECONDS)     //
    void analyseData() {


        LOGGER.info("Job running...");
        try {
            strategyRunner.run();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("JOb errored out...");
        }


    }

}