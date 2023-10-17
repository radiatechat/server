package dev.infernity

import kotlinx.serialization.Serializable
import java.util.UUID

// websocket packet data classes

@Serializable
data class Packet(val id: String, val data: String)

@Serializable
data class MessagePacket(val id: String, val data: String, val user: String)

@Serializable
data class InitializationPacket(val id: String, val data: String, val serverVersion: String, val protocolVersion: String)

// database classes

data class User(val id: Int, val username: String, val uuid: UUID, val creation: Int)

