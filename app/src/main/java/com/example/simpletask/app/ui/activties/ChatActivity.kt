package com.example.simpletask.app.ui.activties

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.simpletask.R
import com.example.simpletask.app.helpers.SocketManger
import com.example.simpletask.domain.models.chat.ChatEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {
    @Inject
    lateinit var socketManger: SocketManger

    private val chatViewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        chatViewModel.getAllMessages()
        getAllMessages()
        kotlin.runCatching {
            socketManger.connect()
        }
    }


    private fun getAllMessages() {
        chatViewModel.allChatMessagesLiveData.observe(this) {
            //update recycler with the new Messages
        }
    }

    // here i will add a new message at the room data base as a receiverAnd i will update the recyclerViewList
    fun receiveAnewMessage() {
        socketManger.registerEventListener("you didn`t mention on the git hub repo the socket url or events names") {
            val gson = Gson()
            val data = it.toString()
            val model: ChatEntity = gson.fromJson(data, ChatEntity::class.java)
            addANewMessageToRoomDataBase(model)
            //update recycler with the new Message
        }
    }


    // create a new object with data which will come from the chatEditText
    // and receiverId Which will you have when you enter the chatActivity from Chat list
    fun sendANewMessage() {
        var chatEntity = createChatEntityObject(
            senderId = "my id or token",
            receiverId = "receiverId",
            data = "currentData",
            messageContent = "message"
        )
        // emit the new message to socketIo
        val gson = Gson()
        val jsonString = gson.toJson(chatEntity)
        val jsonObject = gson.fromJson(jsonString, ChatEntity::class.java)
        socketManger.emit("missed", jsonObject)
        addANewMessageToRoomDataBase(chatEntity)
        //update recycler with the new Message
    }

    private fun addANewMessageToRoomDataBase(chatEntity: ChatEntity) {
        chatViewModel.addNewMessage(chatEntity)
    }


    private fun createChatEntityObject(
        senderId: String,
        receiverId: String,
        data: String,
        messageContent: String
    ) =
        ChatEntity(
            senderId = senderId,
            receiverId = receiverId,
            messageDate = data,
            message = messageContent
        )

    override fun onDestroy() {
        super.onDestroy()
        // and unregister all events
        socketManger.unregisterEventListener("")
        socketManger.disconnect()
    }


}