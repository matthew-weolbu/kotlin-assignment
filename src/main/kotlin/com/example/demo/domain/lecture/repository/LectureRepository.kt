package com.example.demo.domain.lecture.repository

import com.example.demo.domain.lecture.entity.LectureEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LectureRepository : JpaRepository<LectureEntity, Long>, LectureRepositoryCustom {
  fun findByTitle(name: String): LectureEntity?
}
