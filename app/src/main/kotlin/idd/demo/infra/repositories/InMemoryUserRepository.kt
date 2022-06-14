package idd.demo.infra.repositories

import idd.demo.model.textanalyzer.TextAnalyzerService
import idd.demo.model.users.User
import idd.demo.model.users.UserId
import idd.demo.model.users.UserRepository
import java.util.*

class InMemoryUserRepository: UserRepository {
    private val users: MutableMap<String, User> = mutableMapOf()

    override fun save(user: User) {
        users[user.userBasicData.username] = user
    }

    override fun nextId(): UserId {
        return UserId(UUID.randomUUID())
    }

}