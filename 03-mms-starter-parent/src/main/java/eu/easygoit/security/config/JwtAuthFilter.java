package eu.easygoit.security.config;

import eu.easygoit.filter.JwtKmsClientAuthFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * The type Jwt auth filter.
 */
@Slf4j
@Component
public class JwtAuthFilter extends JwtKmsClientAuthFilter {

}
