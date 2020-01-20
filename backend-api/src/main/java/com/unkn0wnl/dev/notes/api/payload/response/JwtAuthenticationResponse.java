package com.unkn0wnl.dev.notes.api.payload.response;

public class JwtAuthenticationResponse {

    public static final String DEFAULT_TOKEN_TYPE = "Bearer";

    private String accessToken;
    private String tokenType;

    public JwtAuthenticationResponse() {
        super();
        tokenType = DEFAULT_TOKEN_TYPE;
    }

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
        tokenType = DEFAULT_TOKEN_TYPE;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
