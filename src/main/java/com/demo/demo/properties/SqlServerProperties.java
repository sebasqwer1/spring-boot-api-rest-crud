package com.demo.demo.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "spring.datasource.sqlserver")
public class SqlServerProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
