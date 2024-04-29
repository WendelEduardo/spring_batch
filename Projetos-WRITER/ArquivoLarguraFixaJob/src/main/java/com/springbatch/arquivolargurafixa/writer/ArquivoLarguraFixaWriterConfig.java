package com.springbatch.arquivolargurafixa.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.arquivolargurafixa.dominio.Cliente;
import org.springframework.core.io.Resource;

@Configuration
public class ArquivoLarguraFixaWriterConfig {

//	new Range[]{new Range(1,10), new Range(11, 20), new Range(21, 23), new Range(24, 43)}
	@StepScope
	@Bean
	public FlatFileItemWriter<Cliente> escritaArquivoLarguraFixaWriter(@Value("#{jobParameters['arquivoClientesSaida']}") Resource arquivoClientesSaida) {
		return new FlatFileItemWriterBuilder<Cliente>()
				.name("escritaArquivoLarguraFixaWriter")
				.resource(arquivoClientesSaida)
				.formatted()
				.format("%-9s %-9s %-2s %-19s")
				.names("nome", "sobrenome", "idade", "email")
				.build();
	}
}
