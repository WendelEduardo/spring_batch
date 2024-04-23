package com.springbatch.skipexception.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.springbatch.skipexception.dominio.Cliente;

@Configuration
public class SkipExceptionStepConfig {
	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;

	public SkipExceptionStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}

	@Bean
	Step skipExceptionStep(ItemReader<Cliente> skipExceptionReader, ItemWriter<Cliente> skipExceptionWriter) {
		return new StepBuilder("skipExceptionStep", jobRepository)
				.<Cliente, Cliente>chunk(11, transactionManager)
				.reader(skipExceptionReader)
				.writer(skipExceptionWriter)
				.faultTolerant()
				.skip(Exception.class)
				.skipLimit(2)
				.build();
	}
}
