package com.wendel.primeiroprojetospringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration
public class BatchConfig {

    @Bean
    public Job imprimeOlaJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("imprimeOlaJob", jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step imprimeOlaStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("imprimeOlaStep", jobRepository)
                .<Integer, String>chunk(1, transactionManager)
                .reader(contaAteDezReader())
                .processor(parOuImparProcessor())
                .writer(imprimeWriter())
                .build();
    }


    private IteratorItemReader<Integer> contaAteDezReader() {
        List<Integer> numerosDeUmAteDez = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        return new IteratorItemReader<Integer>(numerosDeUmAteDez);
    }

    private FunctionItemProcessor<Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<Integer, String>(item -> item % 2 == 0 ? String.format("Item %s é par", item) : String.format("Item %s é impar", item));
    }

    private ItemWriter<String> imprimeWriter() {
        return itens -> itens.forEach(System.out::println);
    }

}
