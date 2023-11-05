package com.clawson.restful.dao;

import com.clawson.restful.model.Employee;
import com.clawson.restful.model.Manager;
import com.clawson.restful.repository.EmployeeRepository;

import com.clawson.restful.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


//@Configuration
@Component
public class DatabaseLoader implements CommandLineRunner{
    
    //private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);
    private final EmployeeRepository employees;
    private final ManagerRepository managers;

     @Autowired
     public DatabaseLoader(EmployeeRepository employees, ManagerRepository managerRepository){
         this.employees = employees;
         this.managers = managerRepository;
     }

     @Override
     public void run(String ... strings) throws Exception{

         Manager chavo = this.managers.save(new Manager("chavo", "lawson", "ROLE_MANAGER"));
         Manager traci = this.managers.save(new Manager("traci", "wilcox", "ROLE_MANAGER"));

         SecurityContextHolder.getContext().setAuthentication(
                 new UsernamePasswordAuthenticationToken("chavo","doesn't matter", AuthorityUtils.createAuthorityList("ROLE_MANAGER"))
         );


         this.employees.save(new Employee("Chavoris", "Lawson", "Billionaire", chavo));
         this.employees.save(new Employee("Bilbo", "Baggins", "burglar", chavo));
	 	this.employees.save(new Employee("Gandalf", "the Grey", "wizard", chavo));


         SecurityContextHolder.getContext().setAuthentication(
                 new UsernamePasswordAuthenticationToken("traci","doesn't matter", AuthorityUtils.createAuthorityList("ROLE_MANAGER"))
         );


         this.employees.save(new Employee("Samwise", "Gamgee", "gardener", traci));
	 	this.employees.save(new Employee("Meriadoc", "Brandybuck", "pony rider", traci));
	 	this.employees.save(new Employee("Peregrin", "Took", "pipe smoker", traci));

         SecurityContextHolder.clearContext();
     }

    //from building rest tutorial
//    @Bean
//    CommandLineRunner initDatabase(EventRepository event, OrderRepository orderRepository) {
//        return args -> {
//            event.save(new Event(
//                "Programming Masterclass",
//                "Advanced Programming",
//                "10/09/2023", "Meetup.com",
//                "$0.00", true, ""));
//            event.save(new Event("Programming Beginners",
//                "Learn how to program",
//                "10/09/2023", "Meetup.com",
//                "$0.00", true, ""));
//
//            event.findAll().forEach(e -> log.info("Preloaded " +e));
//
//            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
//            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
//
//            orderRepository.findAll().forEach(order -> {
//                log.info("Preloaded " + order);
//            });
//        };
//    }
}
