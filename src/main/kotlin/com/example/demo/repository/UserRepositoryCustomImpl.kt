package com.example.demo.repository

import com.example.demo.config.QuerydslRepositorySupport
import com.example.demo.entity.QUserEntity
import com.example.demo.entity.UserEntity

class UserRepositoryCustomImpl : UserRepositoryCustom, QuerydslRepositorySupport(UserEntity::class.java) {
    override fun findByName(name: String): List<UserEntity> {
        return from(QUserEntity.userEntity)
            .where(QUserEntity.userEntity.name.eq(name))
            .fetch()
    }
}
