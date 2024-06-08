package com.example.demo.domain.users.entity

import com.example.demo.domain.users.enums.UserType
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity (
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,
  @Column(nullable = false)
  var name: String,
  @Column(nullable = false, unique = true)
  var email: String,
  var phoneNumber: String,
  @Column(nullable = false)
  var password: String,
  @Column(nullable = false)
  var type: UserType
)