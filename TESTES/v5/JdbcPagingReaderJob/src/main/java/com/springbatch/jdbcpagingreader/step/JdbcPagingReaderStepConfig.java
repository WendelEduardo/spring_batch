package com.springbatch.jdbcpagingreader.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.springbatch.jdbcpagingreader.dominio.Cliente;

@Configuration
public class JdbcPagingReaderStepConfig {
	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;

	public JdbcPagingReaderStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}

	@Bean
	Step jdbcPagingReaderStep(ItemReader<Cliente> jdbcPagingReader, ItemWriter<Cliente> jdbcPagingWriter) {
		return new StepBuilder("jdbcPagingReaderStep", jobRepository)
				.<Cliente, Cliente>chunk(1, transactionManager)
				.reader(jdbcPagingReader)
				.writer(jdbcPagingWriter)
				.build();
	}
}
