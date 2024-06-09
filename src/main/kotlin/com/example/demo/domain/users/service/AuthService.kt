package com.example.demo.domain.users.service

import com.example.demo.domain.users.repository.UserRepository
import com.example.demo.domain.users.requests.AuthenticationRequest
import com.example.demo.domain.users.requests.UserCreateRequest
import com.example.demo.global.security.JwtUtil
import com.example.demo.global.security.MyUserDetailsService
import jakarta.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
  private val authenticationManager: AuthenticationManager,
  private val userDetailsService: MyUserDetailsService,
  private val jwtUtil: JwtUtil,
  private val userRepository: UserRepository,
) {
  fun login(request: AuthenticationRequest): String {
    authenticationManager.authenticate(
      UsernamePasswordAuthenticationToken(request.email, request.password)
    )
    val userDetails: UserDetails = userDetailsService.loadUserByUsername(request.email)
    return jwtUtil.generateToken(userDetails.username)
  }

  @Transactional
  fun register(request: UserCreateRequest) {
    request.password = BCryptPasswordEncoder().encode(request.password)
    val userEntity = UserCreateRequest.toEntity(request)
    userRepository.save(userEntity)
  }
}