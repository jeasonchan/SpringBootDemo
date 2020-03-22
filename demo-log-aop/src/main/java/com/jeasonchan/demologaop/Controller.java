package com.jeasonchan.demologaop;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class Controller {


    @PostMapping(path = "/demo/{name}")
    public Map<String, Object> sayHello(@PathVariable("name") String name,
                                        @RequestParam("age") String age,
                                        @RequestBody List<String> hobbies) {
        Map<String, Object> result = new HashMap<>();
        result.put("PathVariable", name);
        result.put("RequestParam", age);
        result.put("RequestBody", hobbies);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

}
