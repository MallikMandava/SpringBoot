package com.example.mmandava.microservice6.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix="datasourceone.datasource")
public class DataSourceOneConfig {

    private String url;
    private String username;
    private String password;
}
