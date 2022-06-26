package com.citi.abbr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("foo")
public class SpringWithAngularApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWithAngularApplication.class, args);
    }

    @GetMapping("/bar")
    public String hello() {
        return "Hello, World.";
    }
}
