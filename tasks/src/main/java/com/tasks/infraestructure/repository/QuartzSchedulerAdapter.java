package com.tasks.infraestructure.repository;

import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuartzSchedulerAdapter implements SchedulerPort {

    private final Scheduler scheduler;

    public QuartzSchedulerAdapter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public List<String> getJobsRunning() {
        try {
            List<JobExecutionContext> currentlyExecutingJobs = scheduler.getCurrentlyExecutingJobs();
            return currentlyExecutingJobs.stream()
                    .map(ctx -> ctx.getJobDetail().getKey().getName())
                    .collect(Collectors.toList());
        } catch (SchedulerException e) {
            throw new RuntimeException("Error al obtener jobs en ejecución", e);
        }
    }

}
