package com.tasks.infraestructure.repository;

import java.util.List;

public interface SchedulerPort {
    List<String> getJobsRunning();
}
