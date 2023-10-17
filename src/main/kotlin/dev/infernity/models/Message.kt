package dev.infernity.models

import org.jetbrains.exposed.sql.*
import java.util.*

data class Message(val id: Int, val user: UUID, val message: String, val creation: Int, val lastEdit: Int, val channel: String)

object Messages : Table() {
    val id = integer("id").autoIncrement()
    val user = uuid("user")
    val message = varchar("message", 16384)
    val creation = integer("creation")
    val lastEdit = integer("lastEdit")
    val channel = varchar("channel", 256)


    override val primaryKey = PrimaryKey(id)
}