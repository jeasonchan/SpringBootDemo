package com.jeasonchan.dailyexercise.观察者模式实践;

import lombok.Getter;

import java.util.Observable;

public class House extends Observable {
    @Getter
    private String houseName = "House";

    @Getter
    private int price = 0;


    public void setPrice(int newPrice) {
        this.price = newPrice;

        //更改标志，设置已变更状态
        //下一步才能根据已变更的状态发送变量
        super.setChanged();

        //传递想被监控到的变化对象
        super.notifyObservers(newPrice);

    }

    @Override
    public String toString() {
        return "House{" +
                "houseName='" + houseName + '\'' +
                ", price=" + price +
                '}';
    }
}
