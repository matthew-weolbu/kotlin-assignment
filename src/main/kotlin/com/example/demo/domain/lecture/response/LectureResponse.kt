package com.example.demo.domain.lecture.response

import com.example.demo.domain.lecture.entity.LectureEntity

data class LectureResponse(
  val id: Long = 0,
  var title: String,
  var price: Double,
  var maxStudents: Int,
) {
  companion object {
    fun from(lectureEntity: LectureEntity) = LectureResponse(lectureEntity.id, lectureEntity.title, lectureEntity.price, lectureEntity.maxStudents)
  }
}