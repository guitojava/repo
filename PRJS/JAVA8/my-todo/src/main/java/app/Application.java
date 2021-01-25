package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;
import java.io.InputStream;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
//@EnableTransactionManagement
//@EnableVaadin
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {




        SpringApplication.run(Application.class, args);


    }
}