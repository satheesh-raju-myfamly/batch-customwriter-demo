package com.example.batchcustomwriterdemo.reader;

import com.example.batchcustomwriterdemo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class CompositeItemReader implements ItemReader<CompositeModel> {

  private static final Logger log = LoggerFactory.getLogger(CompositeItemReader.class);

  private Iterator<CompositeModel> results;

  @Override
  public CompositeModel read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
    log.info("CompositeItemReader");
    if (this.results == null) {
      List<CompositeModel> list = List.of(getData());
      this.results=list.iterator();
    }
    return this.results.hasNext() ? this.results.next() : null;
  }

  private CompositeModel getData()
  {
    CompositeModel model = new CompositeModel();
    model.setColleges(getColleges());
    model.setStudents(getStudents());
    model.setPersons(getPersons());
    return model;
  }

  private Colleges getColleges()
  {
    return new Colleges(IntStream.range(1,2)
                    .mapToObj(v-> new College("college : "+v))
                    .toList().toArray(College[]::new));
  }

  private Persons getPersons()
  {
    return new Persons(IntStream.range(1,2)
            .mapToObj(v-> new Person("first : "+v,"last:"+v))
            .toList().toArray(Person[]::new));
  }

  private Students getStudents()
  {
    return new Students(IntStream.range(1,2)
            .mapToObj(v-> new Student("student : "+v))
            .toList().toArray(Student[]::new));
  }
}