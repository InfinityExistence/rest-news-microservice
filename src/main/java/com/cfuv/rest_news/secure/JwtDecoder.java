package com.cfuv.rest_news.secure;

import com.cfuv.rest_news.entity.JwtToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JwtDecoder {
    private final Base64.Decoder decoder = Base64.getUrlDecoder();
    private final ObjectMapper om = new ObjectMapper();

    public JwtToken getJwtToken(String jwt) {
        String payload = jwt.split("\\.")[1];
        String decoded = new String(decoder.decode(payload));
        try {
            return om.readValue(decoded, JwtToken.class);
        } catch (Exception ignored) {
        }
        return null;
    }


}
