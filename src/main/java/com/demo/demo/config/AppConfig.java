package com.demo.demo.config;

import com.demo.demo.properties.OracleProperties;
import com.demo.demo.properties.SqlServerProperties;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public OpenAPI openAPI(){
        Info info = new Info().description("Demostración de una api rest con Spring boot, Oracle y Swagger v3.").title("API REST SPRING BOOT").version("v1.0.0");
        return new OpenAPI().info(info);
    }

    @Value("${db.type}")
    private String dbType;

    private final SqlServerProperties sqlProps;
    private final OracleProperties oracleProps;

    public AppConfig(SqlServerProperties sqlProps, OracleProperties oracleProps) {
        this.sqlProps = sqlProps;
        this.oracleProps = oracleProps;
    }

    @Bean
    @Primary
    public DataSource dataSource(){
        if("oracle".equalsIgnoreCase(dbType)){
            return DataSourceBuilder.create()
                    .url(oracleProps.getUrl())
                    .username(oracleProps.getUsername())
                    .password(oracleProps.getPassword())
                    .driverClassName(oracleProps.getDriverClassName())
                    .build();
        } else if("sqlserver".equalsIgnoreCase(dbType)){
            return DataSourceBuilder.create()
                    .url(sqlProps.getUrl())
                    .username(sqlProps.getUsername())
                    .password(sqlProps.getPassword())
                    .driverClassName(sqlProps.getDriverClassName())
                    .build();
        } else {
            return null;
        }
    }
}
