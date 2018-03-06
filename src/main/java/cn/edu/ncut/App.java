package cn.edu.ncut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 */
@EnableAutoConfiguration
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = {"cn.edu.ncut"})
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }
}
