package idd.demo.model.users

interface UserRepository {
    fun save(user: User)
    fun nextId(): UserId

}
