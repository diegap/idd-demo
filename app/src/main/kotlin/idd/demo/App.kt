/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package idd.demo

import idd.demo.actions.RegisterUser
import idd.demo.infra.rest.representations.UserRegistrationNoOkResponse
import idd.demo.infra.rest.representations.UserRegistrationOkResponse
import idd.demo.infra.rest.representations.UserRepresentation
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Application.module() {
    configureRouting()
    configureSerialization()
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
}

val registerUser = RegisterUser()

fun Application.configureRouting() {
    routing {

        get("/") {
            call.respond(HttpStatusCode.OK, "Hello, world!")
        }

        post("/users") {
            val userRepresentation = call.receive<UserRepresentation>()

            val actionResult = with(userRepresentation) {
                registerUser.invoke(username, password, about)
            }

            if (actionResult.isSuccess) {
                call.respond(Created, UserRegistrationOkResponse(actionResult.value))
            } else {
                call.respond(BadRequest, UserRegistrationNoOkResponse(actionResult.value))
            }
        }
    }
}

fun main() {
    embeddedServer(Netty, port = 8080) {
        configureRouting()
    }.start(wait = true)
}