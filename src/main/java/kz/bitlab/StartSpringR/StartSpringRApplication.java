package kz.bitlab.StartSpringR;

import kz.bitlab.StartSpringR.models.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.Date;

@SpringBootApplication
public class StartSpringRApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartSpringRApplication.class, args);
    }


    @Bean(name = "admin")
    @Scope("singleton")
    public User admin() {
        return User.builder().birthDate(new Date()).password("kasya").username("vasya").build();
    }


    @Bean(name = "client")
    @Scope(scopeName = "prototype")
    public User client() {
        return User.builder().birthDate(new Date()).password("client").username("client").build();
    }
}
