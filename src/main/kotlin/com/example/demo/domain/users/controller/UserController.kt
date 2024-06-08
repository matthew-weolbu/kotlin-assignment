package com.example.demo.domain.users.controller

import com.example.demo.domain.users.entity.UserEntity
import com.example.demo.domain.users.response.UserResponse
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.domain.users.service.UserService

@RestController
@RequestMapping("/v1/users")
class UserController(private val userService: UserService) {

  @GetMapping
  fun getAllUsers(): List<UserResponse> {
    val users = userService.findAllUsers()
    return users.map { UserResponse.from(it) }
  }

  @GetMapping("/{id}")
  fun getUserById(@PathVariable id: Long): ResponseEntity<UserResponse> {
    val user = userService.findUserById(id)
    return if (user != null) {
      ResponseEntity(UserResponse.from(user), HttpStatus.OK)
    } else {
      ResponseEntity(HttpStatus.NOT_FOUND)
    }
  }

  @GetMapping("/search")
  fun findUsersByName(@RequestParam name: String): List<UserResponse> {
      val usersByName = userService.findUsersByName(name)
      return usersByName.map { UserResponse.from(it) }
  }
}
