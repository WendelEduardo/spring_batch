package com.udemy.parimparjob.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ParOuImparWriter implements ItemWriter<String>{

    @Override
    public void write(List list) throws Exception {
        list.forEach(System.out::println);
    }

}
