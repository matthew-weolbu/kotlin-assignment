package com.example.demo.domain.users.requests

import com.example.demo.domain.users.entity.UserEntity
import com.example.demo.domain.users.enums.UserType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class UserCreateRequest(
  @field:NotBlank(message = "필수 값 입니다.")
  val name: String,
  @field:NotBlank(message = "필수 값 입니다.")
  val email: String,
  @field:NotBlank(message = "필수 값 입니다.")
  val phoneNumber: String,

  @field:NotBlank(message = "필수 값 입니다.")
  @field:Size(min = 6, max = 10, message = "비밀번호는 6자 이상 10자 이하로 입력해주세요.")
  @field:Pattern(
    regexp = "^(?=.*[a-z])(?=.*[A-Z]).{6,10}$|(?=.*[A-Z])(?=.*\\d).{6,10}$|(?=.*[a-z])(?=.*\\d).{6,10}$",
    message = "비밀번호는 영문 소문자, 대문자, 숫자 중 최소 두 가지 이상 포함해야 합니다."
  )
  var password: String,
  val type: UserType
) {
  companion object {
    fun toEntity(request: UserCreateRequest): UserEntity =
      UserEntity(0L, request.name, request.email, request.phoneNumber, request.password, request.type)
  }
}
