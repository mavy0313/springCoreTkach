package com.maksim_vypov.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.maksim_vypov.spring.core.EventType.ERROR;
import static com.maksim_vypov.spring.core.EventType.INFO;

@Configuration
@PropertySource("classpath:client.properties")
public class AppConfig {

    @Autowired
    LoggersConfig loggersConfig;

    @Value("${id}")
    private String clientId;

    @Value("${name}")
    private String clientName;

    @Value("${greeting}")
    private String clientGreeting;

    @Bean
    public Client client() {
        Client client = new Client(clientId, clientName);
        client.setGreeting(clientGreeting);
        return client;
    }

    @Bean
    @Scope("prototype")
    public Event event() {
        Event event = new Event(new Date(), dateFormat());
        event.setMsg("Some event for 1");
        return event;
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }

    @Bean
    public EventLogger combinedEventLogger() {
        return new CombinedEventLogger(
                Arrays.asList(loggersConfig.consoleEventLogger(), loggersConfig.fileEventLogger()));
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        Map<EventType, EventLogger> map = new HashMap<>();
        map.put(INFO, loggersConfig.consoleEventLogger());
        map.put(ERROR, combinedEventLogger());
        return map;
    }

    @Bean
    public App app() {
        return new App(client(), loggersConfig.consoleEventLogger(), loggerMap());
    }

    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
