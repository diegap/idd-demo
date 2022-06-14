package idd.demo.model.users

import idd.demo.model.textanalyzer.TextAnalyzerService
import idd.demo.model.users.exceptions.ForbiddenWordsDetectedException

class UserService(
    private val userRepository: UserRepository, private val textAnalyzerService: TextAnalyzerService
) {
    fun createUser(userBasicData: UserBasicData): User {
        checkForbiddenWords(userBasicData)
        val user = User(userRepository.nextId(), userBasicData)
        userRepository.save(user)
        return user
    }

    private fun checkForbiddenWords(userBasicData: UserBasicData) {
        if (!textAnalyzerService.isAllowed(userBasicData.about))
            throw ForbiddenWordsDetectedException()
    }

}
