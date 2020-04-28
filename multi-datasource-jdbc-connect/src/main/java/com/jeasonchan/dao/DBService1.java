package com.jeasonchan.dao;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Service("dBService1")

public class DBService1 {
    @Getter
    private DataSource dataSource;

    @Getter
    private JdbcTemplate jdbcTemplate;

    @Getter
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Getter
    private TransactionTemplate defaultTransactionTemplate;

    @Getter
    private ThreadLocal<TransactionTemplate> transactionTemplateHolder = new ThreadLocal<>();

    public void setIsolatoinLevel(int isolatoinLevel) {
        transactionTemplateHolder.set(new TransactionTemplate(
                this.dataSourceTransactionManager,
                new TransactionDefinition() {
                    @Override
                    public int getIsolationLevel() {
                        return isolatoinLevel;
                    }
                }
        ));
    }

    public TransactionTemplate getCustomTransactionTemplate() {
        if (null == this.transactionTemplateHolder.get()) {
            setIsolatoinLevel(TransactionDefinition.ISOLATION_DEFAULT);
        }
        return this.transactionTemplateHolder.get();
    }


    //简化的transactionTemplate的获取流程，向调用者隐藏TransactionTemplate
    public TransactionTemplate setAndGetCustomTransactionTemplate(TransactionDefinition transactionDefinition) {
        this.transactionTemplateHolder.set(new TransactionTemplate(dataSourceTransactionManager, transactionDefinition));
        return this.transactionTemplateHolder.get();

    }


    @Autowired
    public DBService1(@Qualifier("dataSource1") DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        this.defaultTransactionTemplate = new TransactionTemplate(dataSourceTransactionManager);
    }
}
