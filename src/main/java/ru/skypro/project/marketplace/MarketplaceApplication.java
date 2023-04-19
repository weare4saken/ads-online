package ru.skypro.project.marketplace;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class MarketplaceApplication {
  public static void main(String[] args) {
    SpringApplication.run(MarketplaceApplication.class, args);
  }
}
