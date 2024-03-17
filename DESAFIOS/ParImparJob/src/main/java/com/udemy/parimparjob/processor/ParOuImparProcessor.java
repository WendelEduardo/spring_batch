package com.udemy.parimparjob.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParOuImparProcessor implements ItemProcessor {

    @Override
    public FunctionItemProcessor<Integer, String> process(Object o) throws Exception {
        return new FunctionItemProcessor<Integer, String>
                (item -> item % 2 == 0 ? String.format("Item %s é Par", item) : String.format("Item %s é Ímpar", item));
    }

}
