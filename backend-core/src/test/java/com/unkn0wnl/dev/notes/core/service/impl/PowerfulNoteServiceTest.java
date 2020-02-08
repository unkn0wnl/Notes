package com.unkn0wnl.dev.notes.core.service.impl;

import com.unkn0wnl.dev.notes.core.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = PowerfulNoteServiceTest.RepositoryConfig.class)
public class PowerfulNoteServiceTest {

    @Autowired
    public NoteService noteService;

    @BeforeEach
    public void init() {
    }

    @Test
    public void checkMock() {
        assertTrue(MockUtil.isMock(noteService));
    }

    @Test
    public void saveNote() {
    }

    @Test
    public void getAll() {
    }


    @Configuration
    @ComponentScan(basePackages = {"com.unkn0wnl.dev.notes.core.config",
            "com.unkn0wnl.dev.notes.core.entity", "com.unkn0wnl.dev.notes.core.repository"})
    static class RepositoryConfig {
        @Bean
        public NoteService mockedNoteService() {
            return Mockito.mock(NoteService.class);
        }
    }
}