package dev.infernity

import io.ktor.websocket.*
import java.util.concurrent.atomic.*

class Connection(val session: DefaultWebSocketSession, username: String) {
    companion object {
        val lastId = AtomicInteger(0)
    }
    val name = username
}
