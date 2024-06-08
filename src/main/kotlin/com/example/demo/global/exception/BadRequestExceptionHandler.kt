package com.example.demo.global.exception

import jakarta.validation.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class BadRequestExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException::class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String?>> {
    val errors = ex.bindingResult
      .allErrors
      .associateBy({ (it as FieldError).field }, { it.defaultMessage })

    return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
  }

  @ExceptionHandler(ConstraintViolationException::class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  fun handleConstraintViolationExceptions(ex: ConstraintViolationException): ResponseEntity<Map<String, String?>> {
    val errors = ex.constraintViolations
      .associateBy({ it.propertyPath.toString() }, { it.message })

    return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
  }

  @ExceptionHandler(DataIntegrityViolationException::class)
  fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException): ResponseEntity<Map<String, String>> {
    val errorResponse = mapOf("error" to "이메일이 이미 존재합니다.")
    return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
  }

}
