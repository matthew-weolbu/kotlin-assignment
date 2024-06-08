package com.example.demo.domain.lecture.service

import com.example.demo.domain.lecture.entity.LectureEntity
import com.example.demo.domain.lecture.repository.LectureRepository
import org.springframework.stereotype.Service

@Service
class LectureService(private val lectureRepository: LectureRepository) {
  fun findAllLectures(): List<LectureEntity> = lectureRepository.findAll()

  fun findLectureById(id: Long): LectureEntity? = lectureRepository.findById(id).orElse(null)

  fun createLecture(lecture: LectureEntity): LectureEntity = lectureRepository.save(lecture)

  fun updateLecture(id: Long, userDetails: LectureEntity): LectureEntity? {
    val lecture = lectureRepository.findById(id).orElse(null)
    lecture?.let {

      return lectureRepository.save(it)
    }
    return null
  }

  fun deleteUserById(id: Long) {
    lectureRepository.deleteById(id)
  }

  fun findLecturesByTitle(title: String): List<LectureEntity> = lectureRepository.findLecturesByTitle(title)
}