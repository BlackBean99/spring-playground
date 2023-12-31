package com.example.springbatch;

import com.example.springbatch.tasklet.ExecutionContextTasklet1;
import com.example.springbatch.tasklet.ExecutionContextTasklet2;
import com.example.springbatch.tasklet.ExecutionContextTasklet3;
import com.example.springbatch.tasklet.ExecutionContextTasklet4;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.context.annotation.Bean;


@RequiredArgsConstructor
//@Configuration
public class ExecutionContextConfiguration {
  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final ExecutionContextTasklet1 executionContextTasklet1;
  private final ExecutionContextTasklet2 executionContextTasklet2;
  private final ExecutionContextTasklet3 executionContextTasklet3;
  private final ExecutionContextTasklet4 executionContextTasklet4;

  @Bean
  public Flow flow() {
    FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
    flowBuilder.start(step3())
        .next(step4())
        .end();
    return flowBuilder.build();
  }


  @Bean
  public Step step4() {
    return stepBuilderFactory.get("step4")
        .tasklet(executionContextTasklet4)
        .build();
  }

  @Bean
  public Step step3() {
    return stepBuilderFactory.get("step4")
        .tasklet(executionContextTasklet3)
        .build();
  }

  @Bean
  public Job batchJob1() {
    return jobBuilderFactory.get("batchJob1")
      .start(flow())
        .next(step5())
        .end()
      .build();
  }

  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1")
        .tasklet(executionContextTasklet1)
        .build();
  }
  @Bean
  public Step step2(){
    return stepBuilderFactory.get("step2")
        .tasklet(executionContextTasklet2)
        .build();
  }
  @Bean
  public Step step5(){
    return stepBuilderFactory.get("step2")
        .tasklet(
            (contribution, chunkContext) -> {
              System.out.println("name = " + chunkContext.getStepContext().getStepExecution()
                  .getJobExecution().getExecutionContext().get("name"));
              System.out.println("tasklet5");
              return null;
            }

        )
        .build();
  }

}
