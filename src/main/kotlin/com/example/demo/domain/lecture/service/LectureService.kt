package com.example.demo.domain.lecture.service

import com.example.demo.domain.lecture.entity.LectureEntity
import com.example.demo.domain.lecture.repository.LectureRepository
import com.example.demo.domain.users.enums.UserType
import com.example.demo.domain.users.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class LectureService(private val lectureRepository: LectureRepository, private val userRepository: UserRepository) {
  fun findAllLectures(): List<LectureEntity> = lectureRepository.findAll()

  fun findLectureById(id: Long): LectureEntity? =
    lectureRepository.findById(id).orElseThrow { throw IllegalArgumentException("해당 강의가 존재하지 않습니다.") }

  @Transactional
  fun createLecture(currentUser: UserDetails, lecture: LectureEntity): LectureEntity {
    val user = userRepository.findByEmail(currentUser.username)
      ?: throw throw UsernameNotFoundException("해당 이메일($currentUser.username)로 등록된 유저가 존재하지 않습니다.")

    if (user.type != UserType.INSTRUCTOR) {
      throw IllegalAccessException("강의자만 강의를 생성할 수 있습니다.")
    }

    return lectureRepository.save(lecture)
  }

  fun findLecturesByTitle(title: String): List<LectureEntity> = lectureRepository.findLecturesByTitle(title)
}