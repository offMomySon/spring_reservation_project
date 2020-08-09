package kr.or.connect.reservation.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import kr.or.connect.reservation.config.PersistenceJPAConfig;
import kr.or.connect.reservation.controller.CategoryApiController;
import kr.or.connect.reservation.model.Product;

@Import({ PersistenceJPAConfig.class })
@ComponentScan(basePackages = { "kr.or.connect.reservation.dao", "kr.or.connect.reservation.service", "kr.or.connect.reservation.model", "kr.or.connect.reservation.repository" })
@EntityScan("kr.or.connect.reservation.model")
@EnableJpaRepositories("kr.or.connect.reservation.repository") 
@SpringBootApplication
public class JpaApplicationTest implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(JpaApplicationTest.class);

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaApplicationTest.class, args);
	}

	public void run(String... args) throws Exception {

		List<Product> products = productRepository.findAllbyId((long) 1);

		logger.info("Print List!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		for (int i = 0; i < products.size(); i++) {
			logger.info("idx:{}, content:{}", i, products);
		}
	}
}