package idd.demo.actions

class RegisterUser {
    fun invoke(username: String, password: String, about: String): ActionResult {
        TODO("Not yet implemented")
    }

    data class ActionResult(
        val isSuccess: Boolean,
        val value: String
    )
}
