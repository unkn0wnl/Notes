package com.unkn0wnl.dev.notes.api.controller;

import com.unkn0wnl.dev.notes.api.payload.request.SingUpRequest;
import config.WebIntegrationTestConfigurer;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:config/applicationContext-testConfig.xml")
public class AuthenticationControllerTest extends WebIntegrationTestConfigurer {

    @Test
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