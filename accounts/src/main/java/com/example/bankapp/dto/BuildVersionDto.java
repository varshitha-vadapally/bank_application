package com.example.bankapp.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "build")
public record BuildVersionDto(String version,String name) {
}