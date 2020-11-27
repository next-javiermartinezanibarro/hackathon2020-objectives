package com.bbva.hackathon.bbvakids.objective;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ObjectiveApplicationLifeCycle {
    private static final Logger LOGGER = Logger.getLogger(ObjectiveApplicationLifeCycle.class);


    void onStart(@Observes StartupEvent ev) {

        LOGGER.infof("The application Objective is starting with profile `%s`", ProfileManager.getActiveProfile());

    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application Objective is stopping...");
    }
}
