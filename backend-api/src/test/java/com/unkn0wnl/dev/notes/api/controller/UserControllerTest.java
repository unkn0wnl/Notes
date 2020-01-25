package com.unkn0wnl.dev.notes.api.controller;


import config.WebIntegrationTestConfigurer;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:config/applicationContext-testConfig.xml")
public class UserControllerTest extends WebIntegrationTestConfigurer {

    @Test
    @WithMockUser(roles = "USER")
    public void getCurrentUserInfo_WithUser_StatusOkReturnJson() throws Exception {
        mockMvc.perform(
                get("/api/user/me")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

}