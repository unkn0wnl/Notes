package com.unkn0wnl.dev.notes.core.repository;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRepositoryTest {

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"someString"})
    public void someTestMethod() {
        assertTrue(true);
    }

}