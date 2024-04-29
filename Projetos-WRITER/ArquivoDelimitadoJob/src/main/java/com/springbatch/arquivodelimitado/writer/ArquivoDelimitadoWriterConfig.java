package com.springbatch.arquivodelimitado.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbatch.arquivodelimitado.dominio.Cliente;
import org.springframework.core.io.Resource;

@Configuration
public class ArquivoDelimitadoWriterConfig {
	@StepScope
	@Bean
	public FlatFileItemWriter<Cliente> arquivoDelimitadoWriter(
			@Value("#{jobParameters['arquivoClientesSaida']}") Resource arquivoClienteSaida
			) {
		return new FlatFileItemWriterBuilder<Cliente>()
				.name("arquivoDelimitadoWriter")
				.resource(arquivoClienteSaida)
				.delimited()
				.delimiter(";")
				.names("nome", "sobrenome", "idade", "email")
				.build();
	}
}
