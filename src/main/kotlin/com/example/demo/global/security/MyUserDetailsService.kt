package com.example.demo.global.security

import com.example.demo.domain.users.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MyUserDetailsService : UserDetailsService {

  @Autowired
  private lateinit var userRepository: UserRepository

  override fun loadUserByUsername(email: String): UserDetails {
    val user = userRepository.findByEmail(email)
      ?: throw UsernameNotFoundException("User not found with email: $email")

    return User(user.email, user.password, ArrayList())
  }
}
