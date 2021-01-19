package com.github.comrada.forwarder.maintenance;

import com.github.comrada.forwarder.config.ReaderProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Collection;

public final class Maintenance {
    private static final Logger LOGGER = LogManager.getLogger();
    private final TaskScheduler scheduler;
    private final Reader reader;
    private final Publisher publisher;
    private final ReaderProperties properties;

    public Maintenance(TaskScheduler scheduler, Reader reader, Publisher publisher, ReaderProperties properties) {
        this.scheduler = scheduler;
        this.reader = reader;
        this.publisher = publisher;
        this.properties = properties;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        scheduler.schedule(this::cronJob, new CronTrigger(properties.getCron()));
        LOGGER.info("Cron is scheduled '{}'", properties.getCron());
    }

    private void cronJob() {
        Collection<String> messages = reader.read();
        publisher.publish(messages);
    }
}
