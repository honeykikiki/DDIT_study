package com.example.study.react_springboot.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
public class testController {

    @GetMapping("/get")
    public Map<String, Object> get() {
        Map<String, Object> map = new HashMap<>();

        map.put("item", "item");

        return  map;
    }
}
