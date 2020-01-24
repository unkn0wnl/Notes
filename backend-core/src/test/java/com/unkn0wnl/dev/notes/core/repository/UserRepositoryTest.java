package com.unkn0wnl.dev.notes.core.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class UserRepositoryTest {

    @Test
    @DisplayName("some test name")
    @ParameterizedTest
    @ValueSource(strings = {"someString"})
    public void someTestMethod() {
        assertTrue(true);
    }

}