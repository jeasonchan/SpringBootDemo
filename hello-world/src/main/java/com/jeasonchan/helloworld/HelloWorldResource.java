package com.jeasonchan.helloworld;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController  //表明是rest接口，而不是返回一个页面
public class HelloWorldResource {


    //可以直接使用getmapping进行代替，更加简洁
    @RequestMapping(
            path = "/hello/{name}",
            method = RequestMethod.GET
    )
    public Map<String, Object> sayHello(@PathVariable("name") String name,
                                        @RequestParam(required = false) int age,
                                        @RequestBody List<String> hobbies,
                                        @RequestHeader Map<String, Object> header
    ) {
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("age", age);
        result.put("hobbies", hobbies);
        result.put("header", header);

        return result;
    }



    /*
    springboot中，基本的参数类型有三种：
    路径参数，使用 PathVariable  注解，和javax中的pathParam不兼容！
    查询参数，使用 RequestParam   注解，和javax中的queryParam不兼容
    请求体，使用  RequestBody  注解，和javax中的body不兼容
    请求头，使用 RequestHeader 注解，包含客户端类型、token等参数，使用的方式有多种

    =============================
    编译配置




    =============================
    启动配置



     */
}
