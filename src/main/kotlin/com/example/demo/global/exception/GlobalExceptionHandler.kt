package com.example.demo.global.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {
  @ExceptionHandler(Exception::class)
  fun handleAllExceptions(ex: Exception): ResponseEntity<Map<String, String?>> {
    val errors = mapOf("error" to ex.localizedMessage)
    return ResponseEntity(errors, HttpStatus.INTERNAL_SERVER_ERROR)
  }

  @ExceptionHandler(AccessDeniedException::class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  fun handleAccessDeniedException(ex: AccessDeniedException): ResponseEntity<Map<String, String>> {
    val errorResponse = mapOf("error" to "접근 권한이 없습니다.")
    return ResponseEntity(errorResponse, HttpStatus.FORBIDDEN)
  }
}