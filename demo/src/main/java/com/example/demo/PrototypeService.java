package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Configuration
@ComponentScan(basePackages = "demo")
@Scope("prototype")
public class PrototypeService {

    private String dateTime = LocalDateTime.now().toString();

    public String getDate()
    {
          return dateTime;
    }


}
