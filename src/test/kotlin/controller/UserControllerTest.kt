package controller

import com.example.demo.DemoApplication
import com.example.demo.controller.UserController
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest(classes = [DemoApplication::class])
class UserControllerTest {

  @Autowired
  private lateinit var applicationContext: ApplicationContext

  @Test
  fun `UserController is registered as a bean`() {
    val userController = applicationContext.getBean(UserController::class.java)
    assertNotNull(userController)
  }
}
