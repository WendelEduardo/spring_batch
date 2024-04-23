package br.com.wemd.springbatchv5;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class BatchConfig {

    @Autowired
    @Qualifier("transactionManagerApp")
    private PlatformTransactionManager transactionManager;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("oracle.jdbc.OracleDriver");
        dataSourceBuilder.url("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL");
        dataSourceBuilder.username("rm89355");
        dataSourceBuilder.password("261102");
        return dataSourceBuilder.build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public Job job(Step step, JobRepository jobRepository) {
        return new JobBuilder("job", jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step(ItemReader<Pessoa> reader, ItemWriter<Pessoa> writer, JobRepository jobRepository) {
        return new StepBuilder("step", jobRepository)
                .<Pessoa, Pessoa>chunk(200, transactionManager)
                .reader(reader)
                .writer(writer)
                .transactionManager(transactionManager)
                .build();
    }

//    @Bean
//    public ItemReader<Pessoa> reader(@Qualifier("appDS") DataSource dataSource, PessoaRowMapper pessoaRowMapper) {
////        return new FlatFileItemReaderBuilder<Pessoa>()
////                .name("reader")
////                .resource(new FileSystemResource("files/pessoas.csv"))
////                .comments("--")
////                .delimited()
////                .names("nome", "email", "dataNascimento", "idade", "id")
////                .targetType(Pessoa.class)
////                .build();
//
//    }

    @Bean
    public JdbcCursorItemReader<Pessoa> reader(@Qualifier("appDS") DataSource dataSource) {
        JdbcCursorItemReader<Pessoa> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("select * from pessoa");
        reader.setRowMapper(new PessoaRowMapper());
//        reader.setMaxRows(10);
        reader.setFetchSize(10);
        reader.setQueryTimeout(10000);
        return reader;
    }


    @Bean
    public ItemWriter<Pessoa> writer(@Qualifier("appDS") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Pessoa>()
                .dataSource(dataSource)
                .sql(
                        "INSERT INTO pessoa2 (id, nome, email, data_nascimento, idade) VALUES (:id, :nome, :email, :dataNascimento, :idade)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
}
