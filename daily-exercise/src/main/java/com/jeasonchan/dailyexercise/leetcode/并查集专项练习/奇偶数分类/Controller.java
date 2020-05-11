package com.jeasonchan.dailyexercise.leetcode.并查集专项练习.奇偶数分类;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @RequestMapping(method = RequestMethod.POST, path = "/leetcode/UnionFindSet/{implType}")
    public Map<String, Object> solve(@PathVariable("implType") String implType, @RequestBody List<Integer> values) {

        UnionFindSet unionFindSet = new UnionFindSet(values);


        Map<String, Object> result = new HashMap<>();
        result.put("implType", implType);
        result.put("unionFindSet", unionFindSet.getValue2RootArray());
        return result;
    }
}
