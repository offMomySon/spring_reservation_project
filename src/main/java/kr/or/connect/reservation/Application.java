package kr.or.connect.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.config.PersistenceJPAConfig;
import kr.or.connect.reservation.config.WebMvcContextConfiguration;

@ComponentScan(basePackages = { "kr.or.connect.reservation.config", "kr.or.connect.reservation.service", "kr.or.connect.reservation.exception" })
@Import({ApplicationConfig.class, PersistenceJPAConfig.class, WebMvcContextConfiguration.class})
@EnableCaching
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}