package com.example.batchcustomwriterdemo.writer;

import com.example.batchcustomwriterdemo.model.CompositeModel;
import com.example.batchcustomwriterdemo.model.Student;
import com.example.batchcustomwriterdemo.model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class StudentWriter implements ItemWriter<CompositeModel>
{


    @Override
    public void write(Chunk<? extends CompositeModel> chunk) throws Exception {

       log.info("Writing Student : "+chunk.getItems().stream().map(CompositeModel::getStudents)
                .flatMap(data -> Arrays.stream(data.getStudents()))
                .map(Student::getName).collect(Collectors.joining(",")));
    }
}
