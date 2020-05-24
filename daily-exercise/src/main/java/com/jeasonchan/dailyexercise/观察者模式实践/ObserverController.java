package com.jeasonchan.dailyexercise.观察者模式实践;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ObserverController {


    @RequestMapping(method = RequestMethod.POST, path = "/setNewHousePrice")
    public Map<String, Object> setNewHousePrice(@RequestParam(value = "newPrice", required = true) int newPrice) {
        HouseBuyer houseBuyer = new HouseBuyer();

        House house = new House();
        house.addObserver(houseBuyer);


        Map<String, Object> result = new HashMap<>();

        for (int i = 0; i < newPrice; ++i) {
            house.setPrice(i);

            result.put(String.valueOf(i), house.toString());
        }


        //最终输出的其实都是同一个对象，因为都是同一个对象的应用……
        return result;

    }


    public static void main(String[] args) {


        HouseBuyer houseBuyer = new HouseBuyer();

        House house = new House();
        house.addObserver(houseBuyer);

        for (int i = 0; i < 10; ++i) {
            System.out.println("设置房价为：" + i);
            house.setPrice(i);
        }


    }


}
;
