package com.example.mmandava.multidb.service;

import com.example.mmandava.multidb.entity.Person;

public interface personservice {
    public Person save(Person person );

    Iterable<Person> findAll();
}
