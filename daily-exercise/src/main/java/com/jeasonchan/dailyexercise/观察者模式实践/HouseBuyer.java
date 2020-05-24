package com.jeasonchan.dailyexercise.观察者模式实践;

import java.util.Observable;
import java.util.Observer;

public class HouseBuyer implements Observer {


    @Override
    public void update(Observable o, Object arg) {


        System.out.println("被观察的对象是：" + o +
                "被观察者通知的内容是：" + arg);



    }
}
