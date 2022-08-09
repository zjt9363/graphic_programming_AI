package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Zarrow
 */

@Configuration
@ComponentScan({"net"})
@PropertySource("pyCodeProp.properties")
public class SpringConfig {
}
