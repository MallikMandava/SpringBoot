package com.example.mmandava.microservice6.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix="datasourcetwo.datasource")
public class DataSourceTwoConfig {

    private String url;
    private String username;
    private String password;
}
