package com.example.simpletask.domain.models.chat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_messages")
data class ChatEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "sender_id")
    var senderId: String,
    @ColumnInfo(name = "receiver_id")
    var receiverId: String,
    @ColumnInfo(name = "message_date")
    var messageDate: String,
    @ColumnInfo("message_content")
    var message: String
)
