package com.example.demo.domain.users.requests

data class AuthenticationRequest(
  val email: String,
  val password: String
)
