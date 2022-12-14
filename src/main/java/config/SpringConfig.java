package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Zarrow
 */

@Configuration
@ComponentScan({"service"})
@PropertySource("classpath:pyCodeProp.properties")
public class SpringConfig {
}
