package com.example.demo.domain.lecture.controller

import com.example.demo.domain.lecture.requests.LectureCreateRequest
import com.example.demo.domain.lecture.response.LectureResponse
import com.example.demo.domain.lecture.service.LectureService
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/lectures")
class LectureController(private val lectureService: LectureService) {

  @GetMapping
  fun getAllLectures(): List<LectureResponse> {
    val users = lectureService.findAllLectures()
    return users.map { LectureResponse.from(it) }
  }

  @GetMapping("/{id}")
  fun getLectureById(@PathVariable id: Long): ResponseEntity<LectureResponse> {
    val lecture = lectureService.findLectureById(id)
    return if (lecture != null) {
      ResponseEntity(LectureResponse.from(lecture), HttpStatus.OK)
    } else {
      ResponseEntity(HttpStatus.NOT_FOUND)
    }
  }

  @PostMapping
  fun createLecture(@RequestBody lecture: LectureCreateRequest): ResponseEntity<LectureResponse> {
    val newLecture = lectureService.createLecture(LectureCreateRequest.toEntity(lecture))
    return ResponseEntity(LectureResponse.from(newLecture), HttpStatus.CREATED)
  }

  @GetMapping("/search")
  fun findLecturesByTitle(@RequestParam title: String): List<LectureResponse> {
    val lectures = lectureService.findLecturesByTitle(title)
    return lectures.map { LectureResponse.from(it) }
  }
}
