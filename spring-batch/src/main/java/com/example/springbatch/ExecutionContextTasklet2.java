package com.example.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class ExecutionContextTasklet2 implements Tasklet {

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext context)
      throws Exception {
    System.out.println("tasklet2");
    ExecutionContext stepExecutionContext = contribution.getStepExecution().getExecutionContext();
    ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution()
        .getExecutionContext();

    System.out.println(
        "jobExecutionContext.get(\"jobName\") = " + jobExecutionContext.get("jobName"));
    // step간은 공유되지 않기 때문에 step1에서저장된 stepName은 null이다.
    System.out.println(
        "stepExecutionContext.get(\"stepName\") = " + stepExecutionContext.get("stepName"));
    if(stepExecutionContext.get("stepName") == null) {
      stepExecutionContext.put("stepName", context.getStepContext().getStepExecution().getStepName());
    }

    return RepeatStatus.FINISHED;
  }
}
