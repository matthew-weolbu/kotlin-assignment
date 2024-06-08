package com.example.demo.domain.registration.service

import com.example.demo.domain.lecture.repository.LectureRepository
import com.example.demo.domain.lecture.requests.RegistrationCreateRequest
import com.example.demo.domain.registration.dto.RegistrationDTO
import com.example.demo.domain.registration.entity.RegistrationEntity
import com.example.demo.domain.registration.repository.RegistrationRepository
import com.example.demo.domain.users.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class RegistrationService(private val registrationRepository: RegistrationRepository, private val userRepository: UserRepository,
                          private val lectureRepository: LectureRepository
) {
  fun createRegistation(currentUser: UserDetails, request: RegistrationCreateRequest): RegistrationEntity {
    val isLectureExists = lectureRepository.existsById(request.lectureId)

    if (!isLectureExists) {
      throw IllegalArgumentException("강의가 존재하지 않습니다.")
    }

    val user = userRepository.findByEmail(currentUser.username) ?: throw Exception("User not found")
    val registrationDTO = RegistrationCreateRequest.toDto(request.lectureId, user.id)
    val entity = RegistrationDTO.toEntity(registrationDTO)

    return registrationRepository.save(entity)
  }
}