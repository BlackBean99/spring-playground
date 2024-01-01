package com.example.springbatch;

import javax.sql.DataSource;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;

//@Configuration
public class CustomBatchConfiguration extends BasicBatchConfigurer {
  private final DataSource dataSource;
  public CustomBatchConfiguration(
      BatchProperties properties,
      DataSource dataSource,
      TransactionManagerCustomizers transactionManagerCustomizers) {
    super(properties, dataSource, transactionManagerCustomizers);
    this.dataSource = dataSource;
  }

  @Override
  protected JobRepository createJobRepository() throws Exception {
    JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
    // 별도의 커스텀 DataSource를 정할 수 있다.
    factory.setDataSource(dataSource);
    factory.setTransactionManager(getTransactionManager());
    factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");

    return factory.getObject();
  }
}
