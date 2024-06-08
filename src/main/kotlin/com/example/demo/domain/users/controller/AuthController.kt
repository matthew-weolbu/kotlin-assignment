package com.example.demo.domain.users.controller

import com.example.demo.domain.users.repository.UserRepository
import com.example.demo.domain.users.requests.AuthenticationRequest
import com.example.demo.domain.users.requests.UserCreateRequest
import com.example.demo.domain.users.response.AuthenticationResponse
import com.example.demo.global.security.JwtUtil
import com.example.demo.global.security.MyUserDetailsService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/auth")
class AuthController(
  @Autowired private val authenticationManager: AuthenticationManager,
  @Autowired private val jwtUtil: JwtUtil,
  @Autowired private val userDetailsService: MyUserDetailsService,
  @Autowired private val userRepository: UserRepository
) {

  @PostMapping("/login")
  fun login(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<*> {
    authenticationManager.authenticate(
      UsernamePasswordAuthenticationToken(authenticationRequest.email, authenticationRequest.password)
    )
    val userDetails: UserDetails = userDetailsService.loadUserByUsername(authenticationRequest.email)
    print("userDetails.username: " + userDetails.username)
    val jwt = jwtUtil.generateToken(userDetails.username)
    return ResponseEntity.ok(AuthenticationResponse(jwt))
  }

  @PostMapping("/register")
  fun register(@Valid @RequestBody request: UserCreateRequest): ResponseEntity<*> {
    request.password = BCryptPasswordEncoder().encode(request.password)
    val userEntity = UserCreateRequest.toEntity(request)
    userRepository.save(userEntity)
    return ResponseEntity.ok("User registered successfully")
  }

  @PutMapping("/change-password")
  fun changePassword(@RequestParam email: String, @RequestParam newPassword: String): ResponseEntity<*> {
    val user = userRepository.findByEmail(email)
    user?.let {
      it.password = BCryptPasswordEncoder().encode(newPassword)
      userRepository.save(it)
      return ResponseEntity.ok("Password changed successfully")
    }
    return ResponseEntity.badRequest().body("User not found")
  }

  @DeleteMapping("/delete")
  fun deleteUser(@RequestParam email: String): ResponseEntity<*> {
    val user = userRepository.findByEmail(email)
    user?.let {
      userRepository.delete(it)
      return ResponseEntity.ok("User deleted successfully")
    }
    return ResponseEntity.badRequest().body("User not found")
  }
}
