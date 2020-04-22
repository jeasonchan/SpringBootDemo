package com.jeasonchan.dailyexercise;

import com.jeasonchan.dailyexercise.bean.MySqlInfo;
import com.jeasonchan.dailyexercise.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DailyExerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyExerciseApplication.class, args);

        MySqlInfo mySqlInfo = (MySqlInfo) SpringUtil.getBeanByName("mySqlInfo");
        System.out.println(mySqlInfo);


    }

}
