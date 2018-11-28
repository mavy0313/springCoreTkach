package com.maksim_vypov.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggersConfig {

    @Bean
    public EventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean
    public EventLogger fileEventLogger() {
        return new FileEventLogger("loggerFile.txt");
    }

    @Bean
    public EventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger("loggerFile.txt", 2);
    }
}
