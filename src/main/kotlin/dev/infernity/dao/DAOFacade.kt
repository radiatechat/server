package dev.infernity.dao

import dev.infernity.models.*
import java.util.UUID

interface DAOFacade {
    suspend fun allMessages(): List<Message>
    suspend fun message(id: Int): Message?
    suspend fun addNewMessage(user: UUID, message: String): Message?
    suspend fun editMessage(id: Int, message: String): Boolean
    suspend fun deleteMessage(id: Int): Boolean
}