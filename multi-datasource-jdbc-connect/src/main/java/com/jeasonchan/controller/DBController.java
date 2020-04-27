package com.jeasonchan.controller;

import com.jeasonchan.dao.DBService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/sql/")
public class DBController {
    private DBService1 dbService1;

    @Autowired
    public void setDbService1(@Qualifier("dBService1") DBService1 dbService1) {
        this.dbService1 = dbService1;
    }


    @GetMapping(path = "/show/{pathParam}")
    public List<Map<String, Object>> executeShowSql(@PathVariable("pathParam") String pathParam,
                                                    @RequestParam(required = false, name = "extraParam") String requestParam,
                                                    @RequestBody(required = false) Map<String, Object> body) {
        System.out.println("pathParam：" + pathParam + "\n" +
                "requestParam:" + requestParam + "\n" +
                "RequestBody:" + body);

        String showSql = (String) body.getOrDefault("sql", "");

        //默认请款下，jdbctemplate每句sql都是单独的语句，大量执行时会有产生大量的事务，严重降低速度
        return dbService1.getJdbcTemplate().queryForList(showSql);
    }


    //尝试进行事务操作
    @PostMapping(path = "/transaction/setLevel/")
    public int setTransActionLevel(@RequestParam(name = "level") int level) {
        System.out.println(System.identityHashCode(dbService1.getCustomTransactionTemplate()));
        //这一行获取的是隔离等级为默认的-1的TransactionTemplate实例，只不过只存在于当前线程，并且和defaultTransactionTemplate不是同一个对象

        TransactionTemplate defaultTransactionTemplate = dbService1.getDefaultTransactionTemplate();
        System.out.println(System.identityHashCode(defaultTransactionTemplate));


        System.out.println("defaultTransactionTemplate.getIsolationLevel():" + defaultTransactionTemplate.getIsolationLevel());
        System.out.println("System.identityHashCode(defaultTransactionTemplate.getTransactionManager()):" + System.identityHashCode(defaultTransactionTemplate.getTransactionManager()));

        dbService1.setIsolatoinLevel(level);
        TransactionTemplate customTransactionTemplate = dbService1.getCustomTransactionTemplate();
        System.out.println("System.identityHashCode(customTransactionTemplate.getTransactionManager()):" + System.identityHashCode(customTransactionTemplate.getTransactionManager()));


        try {
            customTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    //放事务操作
                }
            });

        } catch (TransactionException e) {
            e.printStackTrace();
        }


        return customTransactionTemplate.getIsolationLevel();
    }
}
