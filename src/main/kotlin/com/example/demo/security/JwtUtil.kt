package com.example.demo.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {
  @Value("\${jwt.secret}")
  private lateinit var SECRET_KEY: String
  @Value("\${jwt.expiration}")
  private val TOKEN_EXPRIATION_TIME = 1000 * 60 * 30

  fun extractUsername(token: String): String {
    return extractClaim(token, Claims::getSubject)
  }

  fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
    val claims = extractAllClaims(token)
    return claimsResolver.invoke(claims)
  }

  private fun extractAllClaims(token: String): Claims {
    return Jwts.parserBuilder().setSigningKey(SECRET_KEY.toByteArray()).build().parseClaimsJws(token).body
  }

  fun generateToken(username: String): String {
    val claims = mutableMapOf<String, Any>()
    return createToken(claims, username)
  }

  private fun createToken(claims: Map<String, Any>, subject: String): String {
    return Jwts.builder()
      .setClaims(claims)
      .setSubject(subject)
      .setIssuedAt(Date(System.currentTimeMillis()))
      .setExpiration(Date(System.currentTimeMillis() + TOKEN_EXPRIATION_TIME))
      .signWith(SignatureAlgorithm.HS256, SECRET_KEY.toByteArray())
      .compact()
  }

  fun validateToken(token: String, username: String): Boolean {
    val extractedUsername = extractUsername(token)
    return extractedUsername == username && !isTokenExpired(token)
  }

  private fun isTokenExpired(token: String): Boolean {
    return extractAllClaims(token).expiration.before(Date())
  }
}
