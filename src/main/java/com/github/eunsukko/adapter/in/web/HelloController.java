package com.github.eunsukko.adapter.in.web;

import com.github.eunsukko.adapter.in.web.dto.HelloResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public HelloResponse hello(@RequestParam("name") String name,
                               @RequestParam("amount") int amount) {
        return new HelloResponse(name, amount);
    }
}
