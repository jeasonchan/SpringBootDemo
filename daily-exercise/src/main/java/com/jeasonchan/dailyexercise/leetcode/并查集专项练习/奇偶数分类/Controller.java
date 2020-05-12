package com.jeasonchan.dailyexercise.leetcode.并查集专项练习.奇偶数分类;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {


    @RequestMapping(method = RequestMethod.POST, path = "/leetcode/UnionFindSet/{implType}")
    @Log(extraValue = "注解上的入参，哈哈哈")
    public Map<String, Object> solve(@PathVariable("implType") String implType, @RequestBody List<Integer> values) {

        UnionFindSet unionFindSet = new UnionFindSet(values);
        List<Integer> rootValues = new ArrayList<>();//已经无法再相互合并的节点结合

        cycle1:
        for (Integer value : values) {
            if (rootValues.isEmpty()) {
                rootValues.add(value);
            } else {

                //将当前节点的代表元和所有代表元逐个比较
                //最终，都不能合并时才最终，加入代表元集合
                //能合并或者同源时，直接退出合并循环
                for (Integer eachRoot : rootValues) {
                    int currentValueRoot = unionFindSet.findRootValue(value);
                    int mergeResult = unionFindSet.compareThenMerge(eachRoot, currentValueRoot);

                    if (UnionFindSet.CAN_NOT_MERGE != mergeResult) {
                        continue cycle1;
                    }


                }

                //走到此处，说明无法和任何现存的代表元合并
                //将自身的代表元加入代表元列表
                rootValues.add(unionFindSet.findRootValue(value));

            }


        }


        Map<String, Object> result = new HashMap<>();
        result.put("implType", implType);
        result.put("unionFindSet", unionFindSet.getValue2RootArray());
        result.put("rootValues", rootValues);
        return result;
    }
}
