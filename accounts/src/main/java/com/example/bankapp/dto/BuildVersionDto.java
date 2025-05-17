package com.example.bankapp.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "build")
@Setter
@Getter
public class BuildVersionDto {
    String version;
    String name;
}