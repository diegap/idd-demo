package idd.demo

import idd.demo.actions.RegisterUser
import idd.demo.model.textanalyzer.TextAnalyzerService
import idd.demo.model.users.*
import io.mockk.mockk
import java.util.*

object TestConstants {

    val userService: UserService = mockk()

    const val username = "thedude2022"
    const val password = "pezzwrd123"
    const val about = "the dude abides"
    const val aboutWithForbiddenWords = "nobody fu#*s with the Jesus!"

    val userId: UUID = UUID.randomUUID()

    val userBasicData = UserBasicData(username, password, about)

    val userBasicDataWithForbiddenWords = userBasicData.copy(about = aboutWithForbiddenWords)

    val user = User(UserId(userId), userBasicData)

    val registerUser = RegisterUser(userService)

    val userRepository: UserRepository = mockk()
    val textAnalyzerService: TextAnalyzerService = mockk()

    val expectedSuccessfulActionResult = RegisterUser.ActionResult(isSuccess = true, userId.toString())

    val expectedFailingActionResult = RegisterUser.ActionResult(
        isSuccess = false, value = "forbidden words detected"
    )


}