//package org.projet_iwa.auth.api;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.projet_iwa.auth.api.controller.UserController;
//import org.projet_iwa.auth.api.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.mock.web.MockServletContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.servlet.ServletContext;
//
//@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = { Main.class })
////@WebMvcTest(controllers = UserController.class)
//public class UserControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    @MockBean
//    private UserService userService;
//
//    @Before
//    public void setup(){
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }
//
//    @Test
//    public void givenWac_whenServletContext_thenItProvidesGreetController() {
//        ServletContext servletContext = wac.getServletContext();
//
//        Assert.assertNotNull(servletContext);
//        Assert.assertTrue(servletContext instanceof MockServletContext);
//        Assert.assertNotNull(wac.getBean("userController"));
//    }
//
//    @Test
//    public void createUser() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
//                .andDo(MockMvcResultHandlers.print());
//    }
//}
