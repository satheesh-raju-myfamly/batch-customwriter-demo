package com.example.batchcustomwriterdemo.writer;

import com.example.batchcustomwriterdemo.model.CompositeModel;
import com.example.batchcustomwriterdemo.model.Person;
import com.example.batchcustomwriterdemo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class PersonWriter implements ItemWriter<CompositeModel>
{


    @Override
    public void write(Chunk<? extends CompositeModel> chunk) throws Exception {
        log.info("Writing Person : "+chunk.getItems().stream().map(CompositeModel::getPersons)
                .flatMap(data -> Arrays.stream(data.getPersons()))
                .map(Person::getFirstName).collect(Collectors.joining(",")));
    }
}
