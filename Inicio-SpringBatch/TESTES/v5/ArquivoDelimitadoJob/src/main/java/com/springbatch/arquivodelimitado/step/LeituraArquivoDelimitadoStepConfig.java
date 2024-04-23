package com.springbatch.arquivodelimitado.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.springbatch.arquivodelimitado.dominio.Cliente;

@Configuration
public class LeituraArquivoDelimitadoStepConfig {
	private JobRepository jobRepository;
	private PlatformTransactionManager transactionManager;

	public LeituraArquivoDelimitadoStepConfig(JobRepository jobRepository,
			PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}

	@Bean
	Step leituraArquivoDelimitadoStep(ItemReader<Cliente> leituraArquivoDelimitadoReader,
			ItemWriter<Cliente> leituraArquivoDelimitadoWriter) {
		return new StepBuilder("leituraArquivoDelimitadoStep", jobRepository)
				.<Cliente, Cliente>chunk(1, transactionManager)
				.reader(leituraArquivoDelimitadoReader)
				.writer(leituraArquivoDelimitadoWriter)
				.build();
	}
}
