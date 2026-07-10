package vn.edu.epu.quanlyhoso.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Tắt bảo vệ CSRF vì xài JWT Token
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/error").permitAll() // ĐẶC BIỆT QUAN TRỌNG: Thả cửa cho mọi API bắt đầu bằng /api/auth (Login)
                // 2. Khu vực Test:
                // Chỉ dành cho người có Role là ADMIN
                .requestMatchers("/api/test/admin").hasAuthority("ADMIN")
                // Chỉ có admin mới là người thay đổi được quyền
                .requestMatchers("/api/admin/**").hasAuthority("ADMIN") 
                // Dành cho bất cứ ai đã đăng nhập (Có Token)
                .requestMatchers("/api/test/user").authenticated()
                .anyRequest().authenticated() // TẤT CẢ các API còn lại bắt buộc phải có Token hợp lệ mới cho vào
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Không lưu phiên (Session) trên Server
            .authenticationProvider(authenticationProvider)
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Xếp ông bảo vệ JWT lên đầu hàng

        return http.build();
    }
}
