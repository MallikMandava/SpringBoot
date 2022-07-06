package com.mmandava.samples.streamexamples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {

    static  List<Employee> emps = new ArrayList<>();
    static{
        emps.add(new Employee(1, "Mallik", "Mandava",List.of("project1" ,"project2") , 5000) );
        emps.add(new Employee(1, "Gayathri", "Mandava",List.of("project3" ,"project1") , 7000) );
        emps.add(new Employee(1, "Abhiram", "Mandava",List.of("project1" ,"project4") , 8000) );
        emps.add(new Employee(1, "Ananya", "Mandava",List.of("project1" ,"project5") , 9000) );


    }
    public static void main(String[] args) {



        //To display the list

      /*  emps.forEach(System.out::println);*/

        // TO display using enhanced for loop

      /*  for (Employee emp : emps) {
            System.out.println(emp);
        }*/

        //To display using stream API


        //Stream API using foreach , which terminal operation
      //  emps.stream().forEach(emp -> System.out.println(emp));


        //map
        // collect
        List<Employee> newemp = emps.stream().map(emp -> new Employee(emp.getEmpId(),emp.getFirstName(),emp.getLastName(),emp.getProjects(),emp.getSal()*1.10)).collect(Collectors.toList());

        newemp.stream().forEach(employee -> System.out.println(employee));

        System.out.println("***************************************");

        // filters
        // YOU can the filtered list on the basis of condition

        List<Employee> fltlisted = emps.stream().filter(employee -> employee.getSal()>7000).map(emp -> new Employee(emp.getEmpId(),emp.getFirstName(),emp.getLastName(),emp.getProjects(),emp.getSal())).collect(Collectors.toList());

        fltlisted.stream().forEach(employee -> System.out.println(employee));

        System.out.println("***************************************");


        //findFirst , get the first element of the list

        Employee findfst = emps.stream().filter(employee -> employee.getSal()>17000).map(emp -> new Employee(emp.getEmpId(),emp.getFirstName(),emp.getLastName(),emp.getProjects(),emp.getSal())).findFirst().orElse(null);
        System.out.println(findfst);
        //findfst.stream().forEach(employee -> System.out.println(employee));


        //flatMap

        String projects = emps.stream().map(employee -> employee.getProjects())
                .flatMap(strings -> strings.stream()).collect(Collectors.joining(","));
        System.out.println(projects);


        // shortcircuit

        List <Employee> scircuit = emps.stream().skip(1).limit(2).collect(Collectors.toList());

        scircuit.stream().forEach(employee -> System.out.println(employee));

        // Finite Data

        Stream.generate(Math::random).limit(5).forEach(val -> System.out.println(val));

        //sorting

        List<Employee> sortedemployess = emps.stream().sorted((o1,o2) -> o1.getFirstName().compareToIgnoreCase(o2.getFirstName())).collect(Collectors.toList());

        sortedemployess.stream().forEach(employee -> System.out.println(employee));


        // min or max

       Employee emp =  emps.stream().max(Comparator.comparing(Employee::getSal)).orElseThrow(NoSuchElementException::new);
        System.out.println("Here is the employess max salary drawn" + emp);

        //reduce

        double totalsal = emps.stream().map(employee -> employee.getSal()).reduce(0.0,Double::sum);

        System.out.println("Total salary is " + totalsal);

    }
}
