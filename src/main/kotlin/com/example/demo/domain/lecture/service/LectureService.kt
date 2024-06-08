package com.example.demo.domain.lecture.service

import com.example.demo.domain.lecture.entity.LectureEntity
import com.example.demo.domain.lecture.repository.LectureRepository
import com.example.demo.domain.users.enums.UserType
import com.example.demo.domain.users.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class LectureService(private val lectureRepository: LectureRepository, private val userRepository: UserRepository) {
  fun findAllLectures(): List<LectureEntity> = lectureRepository.findAll()

  fun findLectureById(id: Long): LectureEntity? = lectureRepository.findById(id).orElse(null)

  fun createLecture(currentUser: UserDetails, lecture: LectureEntity): LectureEntity {
    val user = userRepository.findByEmail(currentUser.username)
      ?: throw throw UsernameNotFoundException("User not found with email: $currentUser.username")

    if (user.type != UserType.INSTRUCTOR) {
      throw IllegalAccessException("강의자만 강의를 생성할 수 있습니다.")
    }

    return lectureRepository.save(lecture)
  }

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