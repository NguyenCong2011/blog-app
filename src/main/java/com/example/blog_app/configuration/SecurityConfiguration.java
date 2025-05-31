package com.example.blog_app.configuration;

import com.example.blog_app.repository.UserRepository;
//import com.example.blog_app.service.UserService.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // Kích hoạt @PreAuthorize
public class SecurityConfiguration {
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http
//            .csrf(AbstractHttpConfigurer::disable)
//            .cors(Customizer.withDefaults()) // Bật CORS
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .authorizeHttpRequests(auth -> auth
//                    .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll() // Cho phép API register không cần login
//                    .anyRequest().authenticated() // Các API khác yêu cầu xác thực
//            )
//            .httpBasic(Customizer.withDefaults()); // Bật Basic Authentication
//
//    return http.build();
//  }

//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//  http
//          .csrf(AbstractHttpConfigurer::disable)
//          .cors(Customizer.withDefaults()) // Bật CORS
//          .sessionManagement(session ->
//                  session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//          .authorizeHttpRequests(auth -> auth
//                  .requestMatchers(HttpMethod.POST ,"/api/v1/auth/register")
//                  .permitAll() // Không yêu cầu đăng nhập
//                  .anyRequest()
//                  .authenticated()
//          );
//  return http.build();
//}


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(customizer->{})
            .sessionManagement(customizer->customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(customize->customize.requestMatchers(HttpMethod.POST,"/api/v1/auth/register")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            )
            .httpBasic(Customizer.withDefaults());
    return http.build();

  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
