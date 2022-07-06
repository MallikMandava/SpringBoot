package com.example.demo;

import org.springframework.context.annotation.Bean;

public class BeanConfig {

    @Bean
    public PrototypeService prototypeService()
    {
        return new PrototypeService();
    }
}
