package com.example.demo.domain.lecture.requests

import com.example.demo.domain.lecture.entity.LectureEntity
import jakarta.validation.constraints.NotBlank

data class LectureCreateRequest(
  @field:NotBlank(message = "필수 값 입니다.")
  val title: String,
  @field:NotBlank(message = "필수 값 입니다.")
  val price: Double,
  @field:NotBlank(message = "필수 값 입니다.")
  val maxStudents: Int,
) {
  companion object {
    fun toEntity(request: LectureCreateRequest): LectureEntity =
      LectureEntity(0L, request.title, request.price, request.maxStudents)
  }
}
