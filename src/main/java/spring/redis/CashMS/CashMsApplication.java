package spring.redis.CashMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CashMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashMsApplication.class, args);
	}

}
