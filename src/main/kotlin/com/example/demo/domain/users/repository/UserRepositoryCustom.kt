package com.example.demo.domain.users.repository

import com.example.demo.domain.users.entity.UserEntity

interface UserRepositoryCustom {
  fun findUsersByName(name: String): List<UserEntity>
}
