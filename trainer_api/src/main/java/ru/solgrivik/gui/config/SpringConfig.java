package ru.solgrivik.gui.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.*;
import ru.solgrivik.spring.hibernate.config.DbConfig;

@Configuration
@Import(DbConfig.class)
@ComponentScan(basePackages = "ru.solgrivik")
@PropertySource("jdbc.properties")

public class SpringConfig {}