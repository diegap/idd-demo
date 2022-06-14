package idd.demo.it

import idd.demo.module
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.restassured.RestAssured
import io.restassured.parsing.Parser
import org.junit.AfterClass
import org.junit.BeforeClass
import java.util.concurrent.TimeUnit

abstract class BaseIT {

    companion object {

        private lateinit var server: ApplicationEngine

        const val UUID_PATTERN = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"

        @BeforeClass
        @JvmStatic
        fun setup() {
            server = embeddedServer(Netty, 8080, module = Application::module)
            server.start()
            Runtime.getRuntime().addShutdownHook(Thread { server.stop(0, 0, TimeUnit.SECONDS) })
            RestAssured.defaultParser = Parser.JSON
        }

        @AfterClass
        @JvmStatic
        fun tearDown(){
            server.stop()
        }
    }

}