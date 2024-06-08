package com.example.demo.domain.users.requests

import com.example.demo.domain.users.entity.UserEntity
import com.example.demo.domain.users.enums.UserType

data class UserCreateRequest(
  val name: String,
  val email: String,
  val phoneNumber: String,
  var password: String,
  val type: UserType
) {
  companion object {
    fun toEntity(request: UserCreateRequest): UserEntity = UserEntity(0L, request.name, request.email, request.phoneNumber, request.password, request.type)
  }
}
