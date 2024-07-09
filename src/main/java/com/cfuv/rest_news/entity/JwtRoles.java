package com.cfuv.rest_news.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class JwtRoles {
    private List<String> roles;

    @Override
    public String toString() {
        return "Item{" +
                "roles=" + roles +
                '}';
    }
}
