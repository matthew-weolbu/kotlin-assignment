package com.example.demo.domain.registration.response

import com.example.demo.domain.registration.entity.RegistrationEntity
import java.time.LocalDateTime

data class RegistrationResponse(
  val id: Long = 0,
  var createdAt: LocalDateTime,
) {
  companion object {
    fun from(entity: RegistrationEntity) = RegistrationResponse(entity.id, entity.createdAt)
  }
}