package kz.bitlab.StartSpringR.configurations;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Assylkhan
 * on 9.09.2019
 * @project StartSpringR
 */
@Configuration
public class ThymleafConfiguration {

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

}
