package com.example.springbatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet1 implements Tasklet {

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext context)
      throws Exception {
    ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution()
        .getExecutionContext();
    ExecutionContext stepExecutionContext = contribution.getStepExecution().getExecutionContext();

    String jobName = context.getStepContext().getStepExecution().getJobExecution().getJobInstance()
        .getJobName();
    String stepName = context.getStepContext().getStepExecution().getStepName();

    if (jobExecutionContext.get("jobName") == null) {
      jobExecutionContext.put("jobName", jobName);
    }
    if(stepExecutionContext.get("stepName") == null) {
      stepExecutionContext.put("stepName", stepName);
    }
    System.out.println("jobName = " + jobName);
    System.out.println("stepName = " + stepName);
    return RepeatStatus.FINISHED;
  }
}
