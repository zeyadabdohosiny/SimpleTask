package com.example.simpletask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpletask.app.constants.AppConstants.SOCKET_URL
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URI

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


}