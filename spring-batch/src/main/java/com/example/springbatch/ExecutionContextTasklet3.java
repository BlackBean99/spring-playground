package com.example.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class ExecutionContextTasklet3 implements Tasklet {

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
      throws Exception {
    Object name = chunkContext.getStepContext().getStepExecution()
        .getJobExecution().getExecutionContext().get("name");

    if (name == null) {
      chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
          .put("name", "user1");
      throw new RuntimeException("name is null");
    }
    return RepeatStatus.FINISHED;
  }
}
