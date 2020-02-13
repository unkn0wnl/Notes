package com.unkn0wnl.dev.notes.core.service.impl;

import com.unkn0wnl.dev.notes.core.repository.NoteRepository;
import com.unkn0wnl.dev.notes.core.repository.RoleRepository;
import com.unkn0wnl.dev.notes.core.repository.UserRepository;
import com.unkn0wnl.dev.notes.core.service.NoteService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration
public class DefaultNoteServiceTest {

    @Autowired
    public NoteService noteService;

    @Autowired
    public UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

    }

    @org.junit.Test
    public void checkMock() {
        assertNotNull(noteService);
//        assertTrue(MockUtil.isMock(noteService));
    }

    @Test
    public void saveNote() {
        assertNotNull(userRepository);
        when(userRepository.findUserByUsernameOrEmail(anyString(), anyString())).thenReturn(Optional.empty());
    }

    @Test
    public void getAll() {
    }


    @Configuration
    @ImportResource(locations = "classpath:applicationContextCore-testConfig.xml")
    static class RepositoryConfig {

        @Bean
        public NoteRepository noteRepository() {
            return Mockito.mock(NoteRepository.class);
        }

        @Bean
        public RoleRepository roleRepository() {
            return Mockito.mock(RoleRepository.class);
        }

        @Bean
        public UserRepository userRepository() {
            return Mockito.mock(UserRepository.class);
        }
    }

}