package com.example.demo.domain.registration.dto

import com.example.demo.domain.registration.entity.RegistrationEntity
import java.time.LocalDateTime

data class RegistrationDTO(
  val id: Long,
  val lectureId: Long,
  val userId: Long,
){
  companion object {
    fun toEntity(dto: RegistrationDTO): RegistrationEntity {
      return RegistrationEntity(0L, dto.lectureId, dto.userId, LocalDateTime.now(), dto.userId)
    }
  }
}
