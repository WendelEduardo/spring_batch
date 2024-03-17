package com.udemy.parimparjob.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ParOuImparReader implements ItemReader {


    @Override
    public IteratorItemReader<Integer> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        List<Integer> numerosDeUmAteDez = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(numerosDeUmAteDez);
        return new IteratorItemReader<Integer>(numerosDeUmAteDez.iterator());
    }


}
