package com.example.mmandava.multidb.service;

//import com.example.mmandava.multidb.EntityManagerUtils;
import com.example.mmandava.multidb.entity.Person;
import com.example.mmandava.multidb.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class PersonImpl implements personservice {

    @Autowired
    private PersonRepository repository;


//    @Autowired
//    private EntityManagerUtils emUtils;

    @Override
    public Person save(Person person) {
       // System.out.println("The repo is repository " + repository.toString());
       // repository = emUtils.getJpaFactory(url).getRepository(PersonRepository.class);
      //  System.out.println("The repositoty to save is " + repository);
        return repository.save(person);
    }

    @Override
    public Iterable<Person> findAll() {
        return repository.findAll();
    }


}
