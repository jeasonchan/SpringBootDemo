package com.jeasonchan.dailyexercise.leetcode.并查集专项练习.奇偶数分类;


import lombok.Getter;

import java.util.List;

/*
使用数组数组作为，元素及其代表元的映射关系载体，
数组的index即代表元素本身的值，
数组的值，代表该值代表元的值，也就是代表元所在的index
 */
public class UnionFindSet {
    public static int CAN_NOT_MERGE = -1;


    @Getter
    private int[] value2RootArray;

    public UnionFindSet(List<Integer> values) {
        initMapArray(values);
    }

    private void initMapArray(List<Integer> values) {
        value2RootArray = new int[9999];

        for (Integer value : values) {
            value2RootArray[value] = value;
        }
    }

    public int findRootValue(int value) {
        int originValue = value;
        int fatherValue = value2RootArray[value];

        while (fatherValue != value) {
            value = fatherValue;
            fatherValue = value2RootArray[value];
        }


        //使用路径压缩，加速下一次查找，切实可以和上面的一次循环进行合并
        while (originValue != fatherValue) {
            int temp = value2RootArray[originValue];
            value2RootArray[originValue] = fatherValue;
            originValue = value2RootArray[temp];
        }


        return fatherValue;

    }


    public int compareThenMerge(int value1, int value2) {
        int value1Root = findRootValue(value1);
        int value2Root = findRootValue(value2);

        //同奇同偶就合并
        if ((value1Root % 2 == 0 && value2Root % 2 == 0) ||
                (value1Root % 2 == 1 && value2Root % 2 == 1)) {

            if (value1Root < value2Root) {
                value2RootArray[value2Root] = value1Root;
                return value1Root;
            }

            if (value2Root < value1Root) {
                value2RootArray[value1Root] = value2Root;
                return value2Root;
            }

            return value1Root;

        }


        return CAN_NOT_MERGE;
    }


}
