package com.example.demo.global.aop

import com.example.demo.global.annotation.DistributedLock
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Aspect
@Component
class DistributedLockAspect(private val redissonClient: RedissonClient) {

  @Around("@annotation(distributedLock)")
  fun around(joinPoint: ProceedingJoinPoint, distributedLock: DistributedLock): Any? {
    val lock: RLock = redissonClient.getLock(distributedLock.lockKey)
    val isLocked: Boolean = lock.tryLock(500, 10000, TimeUnit.MILLISECONDS)

    return if (isLocked) {
      try {
        joinPoint.proceed() // 실제 메서드 실행
      } finally {
        lock.unlock()
      }
    } else {
      throw IllegalStateException("Could not acquire lock")
    }
  }
}