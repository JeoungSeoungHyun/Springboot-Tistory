package site.metacoding.blogv3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import site.metacoding.blogv3.web.UserController;

@EnableJpaAuditing
@SpringBootApplication
public class Blogv3Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Blogv3Application.class, args);
		context.getBean(UserController.class);
	}

}
