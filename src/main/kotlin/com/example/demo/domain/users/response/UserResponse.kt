package com.example.demo.domain.users.response

import com.example.demo.domain.users.entity.UserEntity

data class UserResponse(
  val id: Long = 0,
  var name: String,
  var email: String,
) {
  companion object {
    fun from(userEntity: UserEntity) = UserResponse(userEntity.id, userEntity.name, userEntity.email)
  }
}