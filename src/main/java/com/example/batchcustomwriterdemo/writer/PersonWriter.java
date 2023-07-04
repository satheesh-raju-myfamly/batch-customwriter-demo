package com.example.batchcustomwriterdemo.writer;

import com.example.batchcustomwriterdemo.model.CompositeModel;
import com.example.batchcustomwriterdemo.model.Person;
import com.example.batchcustomwriterdemo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class PersonWriter implements ItemWriter<CompositeModel>
{


    private final JdbcTemplate jdbcTemplate;

    public PersonWriter(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void write(Chunk<? extends CompositeModel> chunk) throws Exception {
        log.info("Writing Person : "+chunk.getItems().stream().map(CompositeModel::getPersons)
                .flatMap(data -> Arrays.stream(data.getPersons()))
                .map(Person::getFirstName).collect(Collectors.joining(",")));
        List<Person> personList = Arrays.stream(chunk.getItems().get(0).getPersons().getPersons()).toList();
        int[][] count = jdbcTemplate.batchUpdate("insert into people(first_name,last_name) values(?,?)",personList,100, new PersonBatchSetter());

        log.info("Inserted : "+count.length);
    }
}
