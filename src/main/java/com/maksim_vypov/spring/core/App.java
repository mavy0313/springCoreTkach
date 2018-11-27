package com.maksim_vypov.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger eventLogger;
    private static ApplicationContext ctx;

    //    private void logEvent(Event event) {
    private void logEvent(String msg, EventType eventType) {
//        String message = msg.replaceAll(client.getId(), client.getFullName());
//        String message = event.getMsg();

        String replacedMessage = msg.replaceAll(client.getId(), client.getFullName());

        Event event = (Event) ctx.getBean("event");
        event.setMsg(replacedMessage);

        eventLogger.logEvent(event);
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
//        App app = new App();

//        app.client = new Client("1", "John Smith");
//        app.eventLogger = new ConsoleEventLogger();

//        app.logEvent("Some event for user 1");

        ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        app.logEvent("Some event for 1", null);
        app.logEvent("Some event for 2", null);

//        Event event = (Event) ctx.getBean("event");
//        app.logEvent(event);
//
//        event = (Event) ctx.getBean("event");
//        app.logEvent(event);

        ((ConfigurableApplicationContext) ctx).close();
    }
}
