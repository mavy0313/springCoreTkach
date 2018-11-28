package com.maksim_vypov.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import static com.maksim_vypov.spring.core.EventType.*;

public class App {

    private Client client;
    private EventLogger defaultLogger;
    private static ApplicationContext ctx;
    private Map<EventType, EventLogger> loggers;

    private void logEvent(EventType eventType, String msg) {
        String replacedMessage = msg.replaceAll(client.getId(), client.getFullName());

        Event event = (Event) ctx.getBean("event");
        event.setMsg(replacedMessage);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
//        ctx = new ClassPathXmlApplicationContext("spring.xml");
        ctx = new AnnotationConfigApplicationContext(LoggersConfig.class, AppConfig.class);

        App app = (App) ctx.getBean("app");

        app.logEvent(null,"Some event for 1");
        app.logEvent(INFO,"Some event for 2");
        app.logEvent(ERROR,"Some event for 3");

        ((ConfigurableApplicationContext) ctx).close();
    }
}
