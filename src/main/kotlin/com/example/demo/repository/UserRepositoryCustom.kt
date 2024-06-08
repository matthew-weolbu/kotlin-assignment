package com.example.demo.repository

import com.example.demo.entity.UserEntity

interface UserRepositoryCustom {
  fun findByName(name: String): List<UserEntity>
}
