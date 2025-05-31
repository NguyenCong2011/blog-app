package com.example.blog_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BlogApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(BlogApp1Application.class, args);
	}

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("́") // Thay thế bằng frontend của bạn
                .allowedMethods("HEAD","OPTIONS","GET", "POST", "PUT", "DELETE","PATCH");
      }
    };
  }

}
