package com.example.demo.domain.registration.repository

import com.example.demo.domain.lecture.repository.RegistrationRepositoryCustom
import com.example.demo.domain.registration.entity.RegistrationEntity
import com.example.demo.global.config.QuerydslRepositorySupport

class RegistrationRepositoryCustomImpl : RegistrationRepositoryCustom, QuerydslRepositorySupport(RegistrationEntity::class.java) {}
