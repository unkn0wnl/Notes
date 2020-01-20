package com.unkn0wnl.dev.notes.core.repository;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnit4ClassRunner.class)
public class UserRepositoryTest {

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"fdsf"})
    public void someTestMethod(String var) {
        int i = 1;
        System.out.println(i | 1);
        assertEquals("fdsf", var);
    }

}