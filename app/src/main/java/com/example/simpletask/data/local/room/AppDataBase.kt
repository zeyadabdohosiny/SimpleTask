package com.example.simpletask.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simpletask.app.constants.AppConstants.DATABASE_VERSION
import com.example.simpletask.data.local.room.dao_interfaces.ChatDao
import com.example.simpletask.domain.models.chat.ChatEntity

@Database(
    entities = [ChatEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}