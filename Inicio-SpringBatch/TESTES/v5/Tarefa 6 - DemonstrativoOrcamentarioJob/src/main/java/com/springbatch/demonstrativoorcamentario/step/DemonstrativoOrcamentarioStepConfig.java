package com.springbatch.demonstrativoorcamentario.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.springbatch.demonstrativoorcamentario.dominio.GrupoLancamento;
import com.springbatch.demonstrativoorcamentario.reader.GrupoLancamentoReader;

@Configuration
public class DemonstrativoOrcamentarioStepConfig {
	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;

	public DemonstrativoOrcamentarioStepConfig(JobRepository jobRepository,
			PlatformTransactionManager transactionManager) {
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}

	@Bean
	Step demonstrativoOrcamentarioStep(
			// Esse aqui lê dos arquivos
			// MultiResourceItemReader<GrupoLancamento> demonstrativoOrcamentarioReader,
			// Esse aqui lê do banco de dados
			GrupoLancamentoReader demonstrativoOrcamentarioReader,
			ItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter) {
		return new StepBuilder("demonstrativoOrcamentarioStep", jobRepository)
				.<GrupoLancamento, GrupoLancamento>chunk(1, transactionManager)
				.reader(demonstrativoOrcamentarioReader)
				.writer(demonstrativoOrcamentarioWriter)
				.build();
	}
}
