package idd.demo.it

import idd.demo.module
import io.ktor.server.testing.*
import io.restassured.http.ContentType.JSON
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.matchesPattern
import org.junit.Test

class UserRegistrationIT : BaseIT() {

    @Test
    fun `register a new user`() = testApplication {

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
            body("id", matchesPattern(UUID_PATTERN))
        }
    }

    @Test
    fun `register a user with forbidden words`() = testApplication {

        application {
            module()
        }

        Given {
            contentType(JSON)
            body(
                """
                    {
                        "username": "jesus.quintana",
                        "password": "eqeWjeriu!2",
                        "about": "nobody fuc*s with the Jesus!"
                    }
                """.trimIndent()
            )

        } When {
            post("/users")

        } Then {
            statusCode(HttpStatus.SC_BAD_REQUEST)
            body("message", `is`("forbidden words detected"))
        }
    }

}