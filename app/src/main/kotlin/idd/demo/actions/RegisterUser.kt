package idd.demo.actions

import idd.demo.model.users.UserBasicData
import idd.demo.model.users.UserService
import idd.demo.model.users.exceptions.ForbiddenWordsDetectedException

class RegisterUser(
    private val userService: UserService
) {
    operator fun invoke(username: String, password: String, about: String): ActionResult =
        try {
            val user = userService.createUser(
                UserBasicData(
                    username, password, about
                )
            )
            ActionResult(isSuccess = true, value = user.id)
        } catch (exception: ForbiddenWordsDetectedException) {
            ActionResult(isSuccess = false, value = "forbidden words detected")
        }

    data class ActionResult(
        val isSuccess: Boolean,
        val value: String
    )
}
