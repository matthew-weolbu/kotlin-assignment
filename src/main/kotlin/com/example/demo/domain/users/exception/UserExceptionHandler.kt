package com.example.demo.domain.users.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class UserExceptionHandler {

  @ExceptionHandler(UsernameNotFoundException::class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  fun handleUsernameNotFoundException(ex: UsernameNotFoundException): ResponseEntity<Map<String, String?>> {
    val errors = mapOf("error" to ex.message)
    return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
  }

}