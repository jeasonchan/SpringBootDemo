package com.jeasonchan.dailyexercise.leetcode.并查集专项练习.奇偶数分类;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

@RestController
public class Controller {

    @RequestMapping(method = RequestMethod.POST, path = "/leetcode/UnionFindSet/{implType}")
    public Map<String, Object> solve(@PathVariable("implType") String implType, @RequestBody List<Integer> values) {

        UnionFindSet unionFindSet = new UnionFindSet(values);
        ConcurrentLinkedQueue<Integer> rootValues = new ConcurrentLinkedQueue<>();

        for (Integer value : values) {
            if (rootValues.isEmpty()) {
                rootValues.add(value);
            } else {
                for (Integer eachRoot : rootValues) {
                    int currentValueRoot = unionFindSet.findRootValue(value);
                    int mergeResult = unionFindSet.compareThenMerge(eachRoot, currentValueRoot);

                    if (UnionFindSet.CAN_NOT_MERGE == mergeResult) {
                        rootValues.add(currentValueRoot);
                        continue;
                    }

                    if (currentValueRoot == mergeResult) {
                        rootValues.remove(eachRoot);
                        rootValues.add(currentValueRoot);
                    }


                }


            }


        }


        Map<String, Object> result = new HashMap<>();
        result.put("implType", implType);
        result.put("unionFindSet", unionFindSet.getValue2RootArray());
        result.put("rootValues", rootValues);
        return result;
    }
}
