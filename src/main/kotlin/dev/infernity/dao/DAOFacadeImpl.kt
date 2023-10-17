package dev.infernity.dao

import dev.infernity.models.Message
import java.util.*

class DAOFacadeImpl : DAOFacade {
    override suspend fun allMessages(): List<Message> {
        TODO("Not yet implemented")
    }

    override suspend fun message(id: Int): Message? {
        TODO("Not yet implemented")
    }

    override suspend fun addNewMessage(user: UUID, message: String): Message? {
        TODO("Not yet implemented")
    }

    override suspend fun editMessage(id: Int, message: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMessage(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}