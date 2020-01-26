package com.unkn0wnl.dev.notes.api.controller;


import config.WebIntegrationTestConfigurer;
import org.apache.logging.log4j.Level;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:config/applicationContext-testConfig.xml")
public class UserControllerTest extends WebIntegrationTestConfigurer {

    public static final String IS_AVAILABLE = "/api/user/isAvailable";

    static {
        loggerLevel = Level.INFO;
    }

    @Test
    @WithMockUser(roles = "USER")
    public void getCurrentUserInfo_WithUser_StatusOkReturnJson() throws Exception {
        mockMvc.perform(
                get("/api/user/me")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void checkUserAvailability_TypeUsernameEmptyUsername_Return() throws Exception {
        mockMvc.perform(
                get(IS_AVAILABLE)
                        .param("type", "username")
                        .param("value", "")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.isAvailable").value("true"));
    }

}