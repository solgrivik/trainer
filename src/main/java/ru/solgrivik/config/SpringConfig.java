package ru.solgrivik.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import ru.solgrivik.Application;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
public class SpringConfig {
    private static final String DB_DRIVER_CLASS_NAME = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:task;DB_CLOSE_DELAY=-1";
    private static final String DB_USER_NAME = "sa";
    private static final String DB_PASSWORD = "";

    @Bean
    @Lazy
    public DataSource dataSource() {
        try {
            BasicDataSource dataSource
                    = new BasicDataSource();
            dataSource.setDriverClassName(DB_DRIVER_CLASS_NAME);
            dataSource.setUrl(DB_URL);
            dataSource.setUsername(DB_USER_NAME);
            dataSource.setPassword(DB_PASSWORD);
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
