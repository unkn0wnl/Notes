package com.unkn0wnl.dev.notes.api.controller;


import com.unkn0wnl.dev.notes.api.config.security.RestWebSecurityConfig;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/applicationContext.xml"),
        @ContextConfiguration("file:src/main/webapp/WEB-INF/rest-servlet.xml"),
        @ContextConfiguration(classes = RestWebSecurityConfig.class)
})
public class UserControllerTest {

    private static Logger LOGGER;

    static {
        LOGGER = getLogger(UserControllerTest.class);
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

    @Rollback
    @Test
    @WithMockUser(roles = "USER")
    public void test_getCurrentUserInfo_ReturnJson() throws Exception {
        mockMvc.perform(get("/api/user/me")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}