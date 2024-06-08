package com.example.demo.domain.registration.controller

import com.example.demo.domain.lecture.requests.RegistrationCreateRequest
import com.example.demo.domain.registration.response.RegistrationResponse
import com.example.demo.domain.registration.service.RegistrationService
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/registration")
class RegistrationController(private val registrationService: RegistrationService) {

  @PostMapping
  fun createRegistration(
    @AuthenticationPrincipal userDetails: UserDetails,
    @RequestBody request: RegistrationCreateRequest
  ): ResponseEntity<RegistrationResponse> {
    val registration =
      registrationService.createRegistation(userDetails, request)
    return ResponseEntity(RegistrationResponse.from(registration), HttpStatus.CREATED)
  }
}
