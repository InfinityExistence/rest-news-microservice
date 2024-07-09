package com.cfuv.rest_news.secure;

import com.cfuv.rest_news.entity.JwtRoles;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Component
public class JwtDecoder {
    private final Base64.Decoder decoder = Base64.getUrlDecoder();
    private final ObjectMapper om = new ObjectMapper();

    public List<String> getRoles(String jwt) {
        if (jwt == null || jwt.isBlank())
            return Collections.emptyList();
        String payload = jwt.split("\\.")[1];
        String decoded = new String(decoder.decode(payload));
        try {
            JwtRoles jwtRoles = om.readValue(decoded, JwtRoles.class);
            return jwtRoles.getRoles();
        } catch (Exception ignored) {
        }
        return Collections.emptyList();
    }


}
