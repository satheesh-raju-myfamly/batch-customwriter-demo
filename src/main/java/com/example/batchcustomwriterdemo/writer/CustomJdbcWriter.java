package com.example.batchcustomwriterdemo.writer;

import com.example.batchcustomwriterdemo.model.Person;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import javax.sql.DataSource;

public class CustomJdbcWriter extends JdbcBatchItemWriter<Person> {
    public CustomJdbcWriter() {
        super();
    }

    @Override
    public void setAssertUpdates(boolean assertUpdates) {
        super.setAssertUpdates(assertUpdates);
    }

    @Override
    public void setSql(String sql) {
        super.setSql(sql);
    }

    @Override
    public void setItemPreparedStatementSetter(ItemPreparedStatementSetter<Person> preparedStatementSetter) {
        super.setItemPreparedStatementSetter(preparedStatementSetter);
    }

    @Override
    public void setItemSqlParameterSourceProvider(ItemSqlParameterSourceProvider<Person> itemSqlParameterSourceProvider) {
        super.setItemSqlParameterSourceProvider(itemSqlParameterSourceProvider);
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public void setJdbcTemplate(NamedParameterJdbcOperations namedParameterJdbcTemplate) {
        super.setJdbcTemplate(namedParameterJdbcTemplate);
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }

    @Override
    public void write(Chunk<? extends Person> chunk) throws Exception {

    }
}
