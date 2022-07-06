package com.example.demo;

import com.example.demo.entity.Department;
import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController


@EnableDiscoveryClient
public class DemoApplication {

	//@Autowired
	//SingletonService singletonService;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);


	}


	/*@GetMapping("/v1/date")
	public List<String> GetDate() throws InterruptedException {

       String first =  singletonService.getMethod();
	   Thread.sleep(1000);
	   String second = singletonService.getMethod();
	   return Arrays.asList(first,second);


	}*/

	/*@GetMapping("/v1/datelegacy")
	public List <String> GetDateLegacy() throws InterruptedException {

		ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

		PrototypeService p1 = context.getBean(PrototypeService.class);

		String first =  p1.getDate();
		Thread.sleep(1000);
		String second = p1.getDate();
		return Arrays.asList(first,second);

	}*/

	@Autowired
	private DepartmentService departmentService;
	private final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);
	@PostMapping("/departments")
	public Department saveDepartment(@Valid @RequestBody  Department department) {
			LOGGER.info("Saving the department");
			return departmentService.saveDepartment(department);

		}

	@GetMapping("/departments")
	public List<Department>fetchDepartmentList() {

		return departmentService.fetchDepartmentList();

	}

	@GetMapping("/departments/name/{name}")
	public Department fetchDepartmentByName(@PathVariable("name") String departmentName){
		return departmentService.fetchDepartmentByName(departmentName);


	}

	@GetMapping("/departments/{departmentId}")
	public Department fetchDepartmentById(@PathVariable("departmentId") Long departmentId) throws DepartmentNotFoundException {
		return departmentService.fetchDepartmentById(departmentId);
	}

	@GetMapping("/departments/code/{code}")
	public Department fetchDepartmentByCode(@PathVariable("code") String departmentCode) throws DepartmentNotFoundException {
		return departmentService.fetchDepartmentCode(departmentCode);

	}
}


