package com.example.demo.domain.users.repository

import com.example.demo.domain.users.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long>, UserRepositoryCustom {
  fun findByName(name: String): UserEntity?
  fun findByEmail(email: String): UserEntity?
}
