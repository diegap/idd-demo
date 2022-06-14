package idd.demo.model.users

import idd.demo.TestConstants.about
import idd.demo.TestConstants.aboutWithForbiddenWords
import idd.demo.TestConstants.textAnalyzerService
import idd.demo.TestConstants.user
import idd.demo.TestConstants.userBasicData
import idd.demo.TestConstants.userBasicDataWithForbiddenWords
import idd.demo.TestConstants.userId
import idd.demo.TestConstants.userRepository
import idd.demo.model.users.exceptions.ForbiddenWordsDetectedException
import io.mockk.every
import io.mockk.verify
import org.junit.Test
import kotlin.test.assertFailsWith

class UserServiceTest {

    @Test
    fun `create a user`() {
        // given
        every { userRepository.nextId() }.returns(UserId(userId))
        every { userRepository.save(user) }.returns(Unit)
        every { textAnalyzerService.isAllowed(about) }.returns(true)

        val userService = UserService(userRepository, textAnalyzerService)

        // when
        userService.createUser(userBasicData)

        // then
        verify(exactly = 1) {
            userRepository.save(user)
        }
    }

    @Test
    fun `attempt to create a user with forbidden words`() {
        // given
        every { textAnalyzerService.isAllowed(aboutWithForbiddenWords) }.returns(false)
        val userService = UserService(userRepository, textAnalyzerService)

        // when
        assertFailsWith<ForbiddenWordsDetectedException> {
            // then
            userService.createUser(userBasicDataWithForbiddenWords)
        }
    }

    @Test
    fun `attempt to create a user with already taken username`() {
        // given

        // when

        // then
    }

}