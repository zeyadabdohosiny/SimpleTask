package com.example.simpletask.data.local.room.dao_interfaces

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simpletask.domain.models.chat.ChatEntity


interface ChatDao {

    @Query("select * from chat_messages")
    suspend fun getAllMessages(): List<ChatEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewChatMessage(chatEntity: ChatEntity)

    @Delete@Query("DELETE FROM chat_messages Where  message_content = :message")
    suspend fun deleteMessage(message:String)
}