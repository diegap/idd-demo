package idd.demo.acceptance

import idd.demo.module
import io.ktor.server.testing.*
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Test

class UserRegistrationIT : BaseIT() {

    @Test
    fun `User registration acceptance`() = testApplication {

        application {
            module()
        }

        Given {
            contentType(JSON)
            body(
                """
                    {
                        "username": "thedude2022",
                        "password": "pezzwrd123",
                        "about": "the dude abides"
                    }
                """.trimIndent()
            )

        } When {
            post("/users")

        } Then {
            statusCode(HttpStatus.SC_CREATED)
            body("id", notNullValue())
        }
    }

}