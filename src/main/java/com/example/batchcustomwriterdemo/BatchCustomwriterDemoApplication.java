package com.example.batchcustomwriterdemo;

import com.example.batchcustomwriterdemo.model.CompositeModel;
import com.example.batchcustomwriterdemo.model.Person;
import com.example.batchcustomwriterdemo.processor.CompositeProcessor;
import com.example.batchcustomwriterdemo.processor.PersonItemProcessor;
import com.example.batchcustomwriterdemo.reader.CompositeItemReader;
import com.example.batchcustomwriterdemo.writer.CollegeWriter;
import com.example.batchcustomwriterdemo.writer.PersonWriter;
import com.example.batchcustomwriterdemo.writer.StudentWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class BatchCustomwriterDemoApplication {

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(BatchCustomwriterDemoApplication.class, args)));
 //       SpringApplication.run(BatchCustomwriterDemoApplication.class, args);

    }

    @Bean
    public CompositeProcessor processor() {
        return new CompositeProcessor();
    }

    @Bean
    public CompositeItemWriter<CompositeModel> writer(DataSource dataSource) {
        CompositeItemWriter<CompositeModel> compositeItemWriter = new CompositeItemWriter<>();
        compositeItemWriter.setDelegates(
                List.of(new CollegeWriter(),new PersonWriter(dataSource),new StudentWriter())
        );
        return compositeItemWriter;
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository,
                             JobCompletionNotificationListener listener, Step step1) {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager, CompositeItemWriter<CompositeModel> writer) {
        return new StepBuilder("step1", jobRepository)
                .<CompositeModel, CompositeModel> chunk(1,transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
    @Bean
    public ItemReader<CompositeModel> reader() {
        return new CompositeItemReader();
    }
}

