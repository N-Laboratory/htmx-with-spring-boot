package jp.nlaboratory.mybatis.sample;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application main class.
 */
@OpenAPIDefinition(info = @Info(title = "Spring Boot 3 (3-tier architecture) with MyBatis",
    version = "1.0.0", description = "Sample project for spring boot 3 with MyBatis. "
    + "This project implement the following. Search/Create/Update/Delete user."))
@SpringBootApplication
public class MybatisSampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisSampleApplication.class, args);
  }

}
