package com.jeasonchan.aopexercise.controller;

import com.jeasonchan.aopexercise.controller.Annotation.Log;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ActionAndObject {

    @PostMapping("/log/{user}")
    @Log(restApiName = "logSth",detail = "haha")
    public Map<String, Object> logSth(@PathVariable("user") String user,
                                      @RequestParam("action") String action,
                                      @RequestBody(required = false) List<Object> objectList) {



        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put(action, objectList);
        System.out.println(result);
        return result;


    }


}
