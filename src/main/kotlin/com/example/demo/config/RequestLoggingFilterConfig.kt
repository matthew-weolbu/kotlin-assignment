package com.example.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter

@Configuration
class RequestLoggingFilterConfig {
  @Bean
  fun logFilter(): CommonsRequestLoggingFilter {
    val filter = CommonsRequestLoggingFilter()
    filter.setIncludeClientInfo(true)
    filter.setIncludeQueryString(true)
    filter.setIncludePayload(true)
    filter.setIncludeHeaders(true)
    filter.setMaxPayloadLength(10000)
    filter.setAfterMessagePrefix("REQUEST DATA: ")
    return filter
  }
}
