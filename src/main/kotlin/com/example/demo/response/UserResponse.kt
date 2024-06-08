package com.example.demo.response

import com.example.demo.entity.UserEntity

data class UserResponse(
  val id: Long = 0,
  var name: String,
  var email: String,
) {
  companion object {
    fun from(userEntity: UserEntity) = UserResponse(userEntity.id, userEntity.name, userEntity.email)
  }
}