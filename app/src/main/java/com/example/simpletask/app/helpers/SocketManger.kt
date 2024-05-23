package com.example.simpletask.app.helpers


import android.util.Log
import com.example.simpletask.app.constants.AppConstants.SOCKET_URL
import io.socket.client.IO
import javax.inject.Inject
import io.socket.client.Socket

class SocketManger @Inject constructor(private var socket: Socket) {

    init {
        // Initialize Socket.io connection
        try {
            val options = IO.Options()
            options.forceNew = true
            socket = IO.socket(SOCKET_URL, options)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun connect() {
        try {
            Log.d("ZOksh","Here")
            socket.connect()
        }catch (x:Exception){

        }

    }

    fun disconnect() {
        socket.disconnect()
    }

    fun registerEventListener(eventName: String, listener: (Any) -> Unit) {
        socket.on(eventName, listener)
    }

    fun unregisterEventListener(eventName: String) {
        socket.off(eventName)
    }

    fun emit(eventName: String, data: Any?) {
        socket.emit(eventName, data)
    }
}




