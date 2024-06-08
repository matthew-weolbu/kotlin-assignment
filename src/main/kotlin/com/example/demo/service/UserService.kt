package com.example.demo.service

import com.example.demo.entity.UserEntity
import org.springframework.stereotype.Service
import com.example.demo.repository.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {
  fun findAllUsers(): List<UserEntity> = userRepository.findAll()

  fun findUserById(id: Long): UserEntity? = userRepository.findById(id).orElse(null)

  fun createUser(user: UserEntity): UserEntity = userRepository.save(user)

  fun updateUser(id: Long, userDetails: UserEntity): UserEntity? {
    val user = userRepository.findById(id).orElse(null)
    user?.let {
      it.name = userDetails.name
      it.email = userDetails.email
      return userRepository.save(it)
    }
    return null
  }

  fun deleteUserById(id: Long) {
    userRepository.deleteById(id)
  }

  fun findUsersByName(name: String): List<UserEntity> = userRepository.findByName(name)
}