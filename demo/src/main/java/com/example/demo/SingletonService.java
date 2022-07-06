package com.example.demo;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class SingletonService {

    public String getMethod()
    {
        return prototypeService().getDate();
    }

    @Lookup
    public PrototypeService prototypeService()
    {
        return null;
    }

}
