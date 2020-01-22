package com.unkn0wnl.dev.notes.api.controller;

import com.unkn0wnl.dev.notes.api.payload.request.SingUpRequest;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:config/applicationContext-testConfig.xml")
public class AuthenticationControllerTest {

    private static final Logger LOGGER;

    static {
        LOGGER = getLogger(AuthenticationControllerTest.class);
    }

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    @Before
    public void init() {
        initMocks(this);
        this.mockMvc = webAppContextSetup(this.webApplicationContext)
                .alwaysDo(print())
                .apply(springSecurity())
                .build();
    }

    @org.junit.Test
    public void authenticateUser() throws Exception {

    }

    @Test
    public void registerUser_ValidUserData_ReturnOk() throws Exception {
        String content = new ObjectMapper().writeValueAsString(new SingUpRequest() {{
            setName("alexey");
            setUsername("alexeeeeey");
            setEmail("validmail@mail.com");
            setPassword("validpass");
        }});

        mockMvc.perform(
                post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}