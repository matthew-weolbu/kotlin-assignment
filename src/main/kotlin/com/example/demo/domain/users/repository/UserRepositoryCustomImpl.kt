package com.example.demo.domain.users.repository

import com.example.demo.config.QuerydslRepositorySupport
import com.example.demo.domain.users.entity.QUserEntity
import com.example.demo.domain.users.entity.UserEntity

class UserRepositoryCustomImpl : UserRepositoryCustom, QuerydslRepositorySupport(UserEntity::class.java) {
    override fun findUsersByName(name: String): List<UserEntity> {
        return from(QUserEntity.userEntity)
            .where(QUserEntity.userEntity.name.eq(name))
            .fetch()
    }
}
