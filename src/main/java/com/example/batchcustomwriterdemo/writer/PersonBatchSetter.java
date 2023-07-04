package com.example.batchcustomwriterdemo.writer;

import com.example.batchcustomwriterdemo.model.Person;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonBatchSetter implements ParameterizedPreparedStatementSetter<Person> {


    @Override
    public void setValues(PreparedStatement ps, Person argument) throws SQLException {
        ps.setString(1, argument.getFirstName());
        ps.setString(2, argument.getLastName());
        ps.addBatch();
    }
}
