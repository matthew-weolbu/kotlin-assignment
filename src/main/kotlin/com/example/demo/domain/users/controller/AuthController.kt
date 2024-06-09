package com.example.demo.domain.users.controller

import com.example.demo.domain.users.requests.AuthenticationRequest
import com.example.demo.domain.users.requests.UserCreateRequest
import com.example.demo.domain.users.response.AuthenticationResponse
import com.example.demo.domain.users.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/auth")
class AuthController(
    private val authService: AuthService
) {

  @PostMapping("/login")
  fun login(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<*> {
    val jwt = authService.login(authenticationRequest)
    return ResponseEntity.ok(AuthenticationResponse(jwt))
  }

  @PostMapping("/register")
  fun register(@Valid @RequestBody request: UserCreateRequest): ResponseEntity<*> {
    authService.register(request)
    return ResponseEntity.ok("User registered successfully")
  }
}
