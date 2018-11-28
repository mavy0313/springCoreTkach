package com.maksim_vypov.spring.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CombinedEventLogger implements EventLogger {

    private Collection<EventLogger> eventLoggers;

    public CombinedEventLogger(List<EventLogger> eventLoggers) {
        this.eventLoggers = new ArrayList<>(eventLoggers);
    }

    public void logEvent(Event event) {
        for (EventLogger logger : eventLoggers) {
            logger.logEvent(event);
        }
    }
}
