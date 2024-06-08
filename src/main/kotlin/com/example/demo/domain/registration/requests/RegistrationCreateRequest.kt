package com.example.demo.domain.lecture.requests

import com.example.demo.domain.registration.dto.RegistrationDTO
import jakarta.validation.constraints.NotBlank

data class RegistrationCreateRequest(
  @field:NotBlank(message = "필수 값 입니다.")
  val lectureId: Long,
) {
  companion object {
    fun toDto(lectureId: Long, userId: Long): RegistrationDTO =
      RegistrationDTO(0L, lectureId, userId)
  }
}
