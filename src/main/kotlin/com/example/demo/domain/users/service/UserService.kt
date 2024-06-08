package com.example.demo.domain.users.service

import com.example.demo.domain.users.entity.UserEntity
import org.springframework.stereotype.Service
import com.example.demo.domain.users.repository.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {
  fun findAllUsers(): List<UserEntity> = userRepository.findAll()

  fun findUserById(id: Long): UserEntity? = userRepository.findById(id).orElse(null)

  fun findUsersByName(name: String): List<UserEntity> = userRepository.findUsersByName(name)
}