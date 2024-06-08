package com.example.demo.domain.lecture.repository

import com.example.demo.domain.lecture.entity.LectureEntity
import com.example.demo.domain.lecture.entity.QLectureEntity
import com.example.demo.global.config.QuerydslRepositorySupport

class LectureRepositoryCustomImpl : LectureRepositoryCustom, QuerydslRepositorySupport(LectureEntity::class.java) {
    override fun findLecturesByTitle(title: String): List<LectureEntity> {
        return from(QLectureEntity.lectureEntity)
            .where(QLectureEntity.lectureEntity.title.eq(title))
            .fetch()
    }
}
