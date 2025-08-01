package com.tasks.infraestructure.scheduler;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail productosJobDetail() {
        return JobBuilder.newJob(ProductosMasVendidosJob.class)
                .withIdentity("productosJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger productosJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(productosJobDetail())
                .withIdentity("productosTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")) // cada 1 minuto
                .build();
    }

    @Bean
    public JobDetail ubicacionJobDetail() {
        return JobBuilder.newJob(ClientesPorUbicacionJob.class)
                .withIdentity("ubicacionJob")
                .storeDurably()
                .build();
    }


     @Bean
    public Trigger ubicacionJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(ubicacionJobDetail())
                .withIdentity("ubicacionTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")) // cada 1 minuto
                .build();
    }

    @Bean
    public JobDetail csvClientJobDetail() {
        return JobBuilder.newJob(CsvExportClientesJob.class)
                .withIdentity("csvClientJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger exportClientCsvTrigger(JobDetail csvClientJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(csvClientJobDetail)
                .withIdentity("csvClientTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")) // cada 1 minuto
                .build();
    }
    @Bean
    public JobDetail csvProductJobDetail() {
        return JobBuilder.newJob(CsvExportProductosJob.class)
                .withIdentity("csvProductJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger exportProductCsvTrigger(JobDetail csvProductJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(csvProductJobDetail)
                .withIdentity("csvProductTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")) // cada 1 minuto
                .build();
    }





}

