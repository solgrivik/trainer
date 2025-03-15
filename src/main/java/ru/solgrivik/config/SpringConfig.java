package ru.solgrivik.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.solgrivik.Application;



@Configuration
@ComponentScan(basePackageClasses = Application.class)
public class SpringConfig {}