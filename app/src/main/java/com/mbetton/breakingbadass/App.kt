package com.mbetton.breakingbadass

import android.app.Application
import androidx.room.Room
import com.mbetton.breakingbadass.database.AppDatabase
import com.mbetton.breakingbadass.database.DATABASE_NAME
import com.mbetton.breakingbadass.repository.BreakingBadApi
import com.mbetton.breakingbadass.repository.CharacterRepository
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var db: AppDatabase
        lateinit var repository: CharacterRepository
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        db = Room.databaseBuilder(this,
            AppDatabase::class.java, DATABASE_NAME)
            .build()

        repository = CharacterRepository()
        repository.syncCharacterNow()
        repository.scheduleCharactersSync()
    }
}