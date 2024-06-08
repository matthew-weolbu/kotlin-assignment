package com.example.demo.global.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtRequestFilter: JwtRequestFilter) {

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }

  @Bean
  fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
    return authenticationConfiguration.authenticationManager
  }

  @Bean
  fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
    http
      .csrf { csrf ->
        csrf.disable()
      }
      .authorizeHttpRequests { auth ->
        auth
          .requestMatchers(HttpMethod.GET, "/public/**").permitAll()
          .requestMatchers(HttpMethod.POST, "/v1/auth/**").permitAll()
          .anyRequest().authenticated()
      }
      .exceptionHandling { exceptionHandling ->
        exceptionHandling.accessDeniedHandler { request, response, accessDeniedException ->
          response.status = HttpStatus.FORBIDDEN.value()
          response.writer.write("해당 리소스에 접근할 권한이 없습니다.")
        }
      }
      .httpBasic { basic ->
        basic.disable()
      }
      .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)

    return http.build()
  }
}
