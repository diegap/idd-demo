package idd.demo.model.users

data class User(val userId: UserId, val userBasicData: UserBasicData) {
    val id get() = userId.value.toString()
}
