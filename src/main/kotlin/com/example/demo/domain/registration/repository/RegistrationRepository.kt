package com.example.demo.domain.registration.repository

import com.example.demo.domain.lecture.repository.RegistrationRepositoryCustom
import com.example.demo.domain.registration.entity.RegistrationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RegistrationRepository : JpaRepository<RegistrationEntity, Long>, RegistrationRepositoryCustom
