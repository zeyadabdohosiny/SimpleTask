package com.example.simpletask.app.ui.activties

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletask.domain.models.chat.ChatEntity
import com.example.simpletask.domain.repository.ChatRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatRepositoryInterface: ChatRepositoryInterface) :
    ViewModel() {
    private val _allChatMessagesLiveData = MutableLiveData<List<ChatEntity>>()
    val allChatMessagesLiveData = _allChatMessagesLiveData


    fun addNewMessage(chatEntity: ChatEntity) {
        viewModelScope.launch {
            chatRepositoryInterface.insertNewChatMessage(chatEntity)
        }
    }

    fun getAllMessages() {
        viewModelScope.launch { _allChatMessagesLiveData.postValue(chatRepositoryInterface.getAllChatMessages()) }
    }

}