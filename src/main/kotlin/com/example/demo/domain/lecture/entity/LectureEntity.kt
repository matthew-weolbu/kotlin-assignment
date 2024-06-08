package com.example.demo.domain.lecture.entity

import jakarta.persistence.*

@Entity
@Table(name = "lectures")
class LectureEntity (
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,
  @Column(nullable = false)
  var title: String,
  @Column(nullable = false)
  var price: Double,
  @Column(nullable = false)
  var maxStudents: Int,
)