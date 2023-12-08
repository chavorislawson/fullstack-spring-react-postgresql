package com.clawson.restful;

import com.clawson.restful.model.Employee;
import com.clawson.restful.model.Manager;
import com.clawson.restful.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.hateoas.MediaTypes;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class IntegrationTests {
//    @Autowired
//    public TestRestTemplate restTemplate;

    @Autowired
    MockMvc mvc;

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private WebApplicationContext context;

    //@Autowired
    //private Manager manager; //probably because they're not interfaces. They are the type, nothing is depending on this
    //@Autowired
    //private Employee employee;
    //@Autowired
    private Authentication auth;

    @BeforeEach
    public void setup(){
        //this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        // this.manager = new Manager("Hamilton", "Lawson", "ROLE_MANAGER");
        // this.employee = new Employee("Chavo", "Lawson", "Test", this.manager);

        // SecurityContextHolder.getContext().setAuthentication(
        //         new UsernamePasswordAuthenticationToken("chavo","doesn't matter", AuthorityUtils.createAuthorityList("ROLE_MANAGER")));
        // this.auth = SecurityContextHolder.getContext().getAuthentication();
    }

    /*
    Test repo attributes
    test crud operations on repo
    test authentication
    test authorization
    test websocket?
    maybe other things
     */

    @Test
    public void testGetEmployeesSuccess() throws Exception{

        mvc.perform(get("/api/employees").contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEmployeesManagerSuccess() throws Exception{

        mvc.perform(get("/api/employees").contentType(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$._embedded.employees[0].manager.name", is("chavo")));
    }

    @Test
    @WithMockUser(value = "chavo")
    public void testGetEmployeesWithMockUserForbidden() throws Exception{

        mvc.perform(get("/api/employees").contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(value = "chavo")
    public void testGetEmployeesWithMockUserSuccess() throws Exception{

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("chavo","doesn't matter", AuthorityUtils.createAuthorityList("ROLE_MANAGER")));
        this.auth = SecurityContextHolder.getContext().getAuthentication();

        mvc.perform(get("/api/employees").contentType(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
                .andDo(print())
                .andExpect(jsonPath("$._embedded.employees[0].manager.name", is(this.auth.getName())));
                //how to get the session attrs for this request
                //.andExpect(jsonPath("$[0].firstName", is(this.employee.getFirstName())));
        //fail()
    }

    

}
