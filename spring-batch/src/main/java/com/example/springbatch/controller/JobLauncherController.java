package com.example.springbatch.controller;

import com.example.springbatch.domain.User;
import java.util.Date;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {
  @Autowired
  private JobLauncher jobLauncher;
//  @Autowired
//  private Job job;
//
//  @GetMapping("/launchjob")
//  public String handle(@RequestBody User member)
//      throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//    JobParameters jobParameters = new JobParametersBuilder()
//        .addString("id", String.valueOf(member.getId()))
//        .addDate("date", new Date())
//        .toJobParameters();
//    ((SimpleJobLauncher) jobLauncher).setTaskExecutor(new SimpleAsyncTaskExecutor());
//    jobLauncher.run(job, jobParameters);
//    return "JobLauncherController";
//  }
}
