package com.example.simpletask.domain.repository

import com.example.simpletask.domain.models.chat.ChatEntity

interface ChatRepositoryInterface {

    suspend fun getAllChatMessages(): List<ChatEntity>
    suspend fun insertNewChatMessage(chatMessage: ChatEntity)
    suspend fun deleteChatMessage(message:String)
}