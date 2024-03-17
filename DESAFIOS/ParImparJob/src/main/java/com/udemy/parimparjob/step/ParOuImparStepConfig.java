package com.udemy.parimparjob.step;

import com.udemy.parimparjob.processor.ParOuImparProcessor;
import com.udemy.parimparjob.reader.ParOuImparReader;
import com.udemy.parimparjob.writer.ParOuImparWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParOuImparStepConfig {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step imprimeParImparStep(ParOuImparReader parOuImparReader, ParOuImparProcessor parOuImparProcessor, ParOuImparWriter parOuImparWriter) {
        return stepBuilderFactory
                .get("imprimeParImparStep")
                .<Integer, String>chunk(1)
                .reader(parOuImparReader)
                .processor(parOuImparProcessor)
                .writer(parOuImparWriter)
                .build();
    }

}
