package dev.infernity.plugins

import dev.infernity.Connection
import dev.infernity.InitializationPacket
import dev.infernity.Packet
import dev.infernity.MessagePacket
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import org.slf4j.LoggerFactory.getLogger
import java.time.*
import java.util.*
import kotlin.collections.LinkedHashSet
import kotlinx.serialization.*
import kotlinx.serialization.json.*


fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    routing {
        val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())
        webSocket("/chat") {
            send(Json.encodeToString(InitializationPacket("info", "Radiate Server 3.1 / ICP 2.1.1", "3.1", "2.1.1")))
            val usernameFrame = incoming.receive()
            println(usernameFrame)
            if ((usernameFrame as? Frame.Text) == null) {
                send(Json.encodeToString(Packet("response_system_message", "Your username is invalid!")))
            }
            val username = (usernameFrame as Frame.Text).readText()
            val trimmedUsername = username.trim()
            val thisConnection = Connection(this, trimmedUsername)
            connections += thisConnection
            try {
                send(Json.encodeToString(Packet("response_system_message", "You are connected! There are ${connections.count()} users here.")))
                for (frame in incoming) {
                    println(connections)
                    frame as? Frame.Text ?: continue
                    val receivedText = frame.readText()
                    val trimmedText = receivedText.trim()
                    val packet = Json.decodeFromString<Packet>(trimmedText)
                    when(packet.id){
                        "send_chat_message" -> {
                            val trimmedPacketData = packet.data.trim()
                            val textWithUsername = "[${thisConnection.name}]: $trimmedPacketData"
                            getLogger("text_backup").info(textWithUsername)
                            connections.forEach {
                                it.session.send(Json.encodeToString(MessagePacket("response_chat_message", trimmedPacketData, thisConnection.name/*, messageID*/)))
                            }
                        }}
                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                println("Removing $thisConnection!")
                connections -= thisConnection
            }
        }
    }
}
