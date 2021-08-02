package com.mbetton.breakingbadass.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mbetton.breakingbadass.Character

const val DATABASE_NAME = "character"

@Database(entities = [Character::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao() : CharacterDao
}