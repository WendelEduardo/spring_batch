package com.udemy.parimparjob.batchConfig;

import com.udemy.parimparjob.processor.ParOuImparProcessor;
import com.udemy.parimparjob.reader.ParOuImparReader;
import com.udemy.parimparjob.writer.ParOuImparWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ParImparBatchConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Bean
	public Job imprimeParImparJob(JobRepository jobRepository, Step step) {
		return jobBuilderFactory.get("imprimeParImparJob").start(step)
				.incrementer(new RunIdIncrementer()).build();
	}

}
