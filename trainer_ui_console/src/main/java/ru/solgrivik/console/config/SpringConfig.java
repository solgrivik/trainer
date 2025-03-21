package ru.solgrivik.console.config;

import org.springframework.context.annotation.*;
import spring.jdbc.config.DbConfig;

@Configuration
@Import(DbConfig.class)
@ComponentScan(basePackages = "ru.solgrivik")
@PropertySource("jdbc.properties")
public class SpringConfig {}