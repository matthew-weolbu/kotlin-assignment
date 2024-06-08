package com.example.demo.global.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Slf4j
@Component
class JwtRequestFilter(
  @Autowired private val userDetailsService: UserDetailsService,
  @Autowired private val jwtUtil: JwtUtil
) : OncePerRequestFilter() {
  @Value("\${jwt.header}")
  private lateinit var AUTHORIZATION_HEADER: String
  @Value("\${jwt.prefix}")
  private lateinit var AUTHORIZATION_PREFIX: String


  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    val authorizationHeader = request.getHeader(AUTHORIZATION_HEADER)
    var email: String? = null
    var jwt: String? = null

    if (authorizationHeader != null && authorizationHeader.startsWith(AUTHORIZATION_PREFIX)) {
      jwt = authorizationHeader.substring(7)
      email = jwtUtil.extractUsername(jwt)
    }

    if (email != null && SecurityContextHolder.getContext().authentication == null) {
      val userDetails = this.userDetailsService.loadUserByUsername(email)
      if (jwtUtil.validateToken(jwt!!, userDetails.username)) {
        val authenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authenticationToken
      }
    }
    filterChain.doFilter(request, response)
  }
}
