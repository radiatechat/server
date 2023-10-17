package dev.infernity

import dev.infernity.dao.* // data access object, not the crypto dao or whatever
import dev.infernity.plugins.*
import dev.infernity.plugins.configureRouting
import dev.infernity.plugins.configureSockets
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 25565, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    // DatabaseFactory.init()
    configureSockets()
    configureRouting()
}
