package com.example.demo.global.aop

import com.example.demo.global.annotation.DistributedLock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.redisson.api.RedissonClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Service
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.lang.Thread.sleep
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

@Service
class ExampleService {

  @DistributedLock(lockKey = "exampleLock")
  fun process(task: () -> Unit) {
    // 동기화된 작업 수행
    task()
  }
}

@ExtendWith(SpringExtension::class)
@SpringBootTest
class DistributedLockTest {

  @Autowired
  private lateinit var exampleService: ExampleService

  @Autowired
  private lateinit var redissonClient: RedissonClient

  @Test
  fun `test distributed lock`() {
    val numberOfThreads = 10
    val latch = CountDownLatch(numberOfThreads)
    val executorService = Executors.newFixedThreadPool(numberOfThreads)
    val counter = AtomicInteger(0)

    for (i in 0 until numberOfThreads) {
      executorService.submit {
        try {
          exampleService.process {
            val value = counter.incrementAndGet()
            println("Counter: $value")
          }
        } finally {
          latch.countDown()
        }
      }
    }

    latch.await()
    executorService.shutdown()

    // 최종 카운터 값이 numberOfThreads와 같아야 함
    assertEquals(numberOfThreads, counter.get())
  }
}