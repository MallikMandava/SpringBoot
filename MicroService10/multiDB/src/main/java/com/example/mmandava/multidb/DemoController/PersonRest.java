package com.example.mmandava.multidb.DemoController;

import com.example.mmandava.multidb.DataBaseEngine.DataSourceContextHolder;
import com.example.mmandava.multidb.DataBaseEngine.DataSourceEnum;
//import com.example.mmandava.multidb.EntityManagerUtils;
import com.example.mmandava.multidb.entity.Person;
import com.example.mmandava.multidb.repository.PersonRepository;
import com.example.mmandava.multidb.service.PersonImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor

public class PersonRest {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonImpl pservice;



//    @Autowired
//    private EntityManagerUtils emUtils;

   private final DataSourceContextHolder dataSourceContextHolder;

    @Autowired
    private HttpServletRequest context;




    @RequestMapping(value = "/all")
    public Iterable<Person> getAll(){
        setRepository(context.getRequestURL().toString());
        return pservice.findAll();
    }

    @PostMapping("/persons")
    public Person  savePerson(@RequestBody Person person ) {


        setRepository(context.getRequestURL().toString());

        return  pservice.save(person );
    }

    private void setRepository(String url){
        if ( url.contains("main")) {
            System.out.println("First DataBAse");
            dataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_ONE);
        }
        else {
            System.out.println("second DataBAse");
            dataSourceContextHolder.setBranchContext(DataSourceEnum.DATASOURCE_TWO);
        }

    }

    @GetMapping("/welcome")
    public String Welcome(){
        return "Welcome Page";
    }
}
