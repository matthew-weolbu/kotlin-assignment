package com.example.demo.domain.lecture.repository

import com.example.demo.domain.lecture.entity.LectureEntity

interface LectureRepositoryCustom {
  fun findLecturesByTitle(title: String): List<LectureEntity>
}
