package com.jeasonchan.DB;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("dBService2")
@Getter
public class DBService2 {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    public DBService2(@Qualifier("dataSource2") DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
    }
}
