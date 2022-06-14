package idd.demo.actions

import idd.demo.TestConstants.about
import idd.demo.TestConstants.aboutWithForbiddenWords
import idd.demo.TestConstants.expectedFailingActionResult
import idd.demo.TestConstants.expectedSuccessfulActionResult
import idd.demo.TestConstants.password
import idd.demo.TestConstants.registerUser
import idd.demo.TestConstants.user
import idd.demo.TestConstants.userBasicData
import idd.demo.TestConstants.userService
import idd.demo.TestConstants.username
import idd.demo.model.users.exceptions.ForbiddenWordsDetectedException
import io.mockk.every
import org.junit.Assert.*
import org.junit.Test

class RegisterUserTest {

    @Test
    fun `register a user has success`(){
        // given
        every {
            userService.createUser(
                userBasicData
            )
        }.returns(user)

        // when
        val actionResult = registerUser(username, password, about)

        // then
        assertEquals(expectedSuccessfulActionResult, actionResult)

    }

    @Test
    fun `register a user fail`(){
        // given
        every {
            userService.createUser(
                userBasicData.copy(about = "nobody fu#*s with the Jesus!")
            )
        }.throws(ForbiddenWordsDetectedException())

        // when
        val actionResult = registerUser(username, password, aboutWithForbiddenWords)

        // then
        assertEquals(expectedFailingActionResult, actionResult)

    }

}