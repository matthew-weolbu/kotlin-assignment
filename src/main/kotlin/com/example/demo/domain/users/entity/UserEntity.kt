package com.example.demo.domain.users.entity

import com.example.demo.domain.users.enums.UserType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserEntity (
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,
  var name: String,
  var email: String,
  var phoneNumber: String,
  var password: String,
  var type: UserType
)