package vn.edu.epu.quanlyhoso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Chú ý: Cần import thêm HeaderAuthenticationFilter của bạn ở đây nếu file khác package

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Cách mới thay thế cho @EnableGlobalMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Cách viết Lambda mới của Spring Boot 3 để disable CSRF
            .csrf(csrf -> csrf.disable()) 
            
            // Giữ nguyên logic Filter của bạn
            .addFilterBefore(new HeaderAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            
            // Cấu hình phân quyền kiểu mới
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Tạm thời cho phép tất cả, @PreAuthorize sẽ kiểm soát sau
            );
            
        return http.build();
    }
}