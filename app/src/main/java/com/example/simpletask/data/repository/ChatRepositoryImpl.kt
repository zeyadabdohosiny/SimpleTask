package com.example.simpletask.data.repository

import com.example.simpletask.data.local.room.dao_interfaces.ChatDao
import com.example.simpletask.domain.models.chat.ChatEntity
import com.example.simpletask.domain.repository.ChatRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChatRepositoryImpl(private val chatDao: ChatDao) : ChatRepositoryInterface {
    override suspend fun getAllChatMessages() = withContext(Dispatchers.IO) {
        chatDao.getAllMessages()
    }

    override suspend fun insertNewChatMessage(chatMessage: ChatEntity) {
        chatDao.addNewChatMessage(chatMessage)
    }

    override suspend fun deleteChatMessage(message: String) {
        chatDao.deleteMessage(message)
    }
}