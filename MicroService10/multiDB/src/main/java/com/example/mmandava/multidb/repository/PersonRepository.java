package com.example.mmandava.multidb.repository;

import com.example.mmandava.multidb.entity.Person;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Repository

//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public interface PersonRepository extends CrudRepository<Person, Long> {



}
