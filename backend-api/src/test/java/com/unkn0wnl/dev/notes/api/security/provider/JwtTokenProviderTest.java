package com.unkn0wnl.dev.notes.api.security.provider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.MockUtil;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class JwtTokenProviderTest {

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Test
    void validateToken() {
        assertTrue(MockUtil.isMock(jwtTokenProvider));
    }
}