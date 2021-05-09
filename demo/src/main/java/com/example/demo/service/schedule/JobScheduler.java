package com.example.demo.service.schedule;

import com.example.demo.infrastructure.config.batch.SimpleJobConfiguration;
import com.example.demo.infrastructure.enums.LeftReservationDate;
import com.example.demo.presentation.dto.ReservationSearchDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.example.demo.infrastructure.enums.LeftReservationDate.ONEDAYBEFORE;
import static com.example.demo.infrastructure.enums.LeftReservationDate.WEEKBEFORE;

@Slf4j
@Component
public class JobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    private final int scheduleInterval = 1;

    @Scheduled(cron = "* */1 * * * *")
    public void runJob() {
        LocalDateTime now = LocalDateTime.now();

        doRunJob(ONEDAYBEFORE.getStartTimeInclusive(now, scheduleInterval));

        doRunJob(WEEKBEFORE.getStartTimeInclusive(now, scheduleInterval));
    }

    private void doRunJob(ReservationSearchDate reservationSearchDate){
        JobParameters parameters = new JobParametersBuilder()
                .addString("startTimeInclusive", reservationSearchDate.getStartTimeInclusive().toString())
                .addString("endTimeExclusive", reservationSearchDate.getEndTimeExclusive().toString())
                .toJobParameters();

        try {
            jobLauncher.run(job, parameters);
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {
            log.error(e.getMessage());
        }
    }

}