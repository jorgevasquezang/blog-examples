package com.jovasquez.service;

import com.jovasquez.bean.Person;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by jvasquez on 10/10/2016.
 */
@Component
public class PersonItemWriter extends JdbcBatchItemWriter<Person> {


    public PersonItemWriter(DataSource dataSource) {
        setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
        setDataSource(dataSource);
    }

}
