package com.example.demo.domain.registration.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "registrations")
class RegistrationEntity (
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,
  @Column(nullable = false)
  val lectureId: Long,
  @Column(nullable = false)
  val userId: Long,
  val createdAt: LocalDateTime,
  val createdBy: Long,
)