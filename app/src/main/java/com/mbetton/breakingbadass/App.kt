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
        val api = BreakingBadApi()
    }


//    val retrofit = Retrofit.Builder()
//        .baseUrl("https://www.breakingbadapi.com/api/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

   // val breakingBadService: BreakingBadService = retrofit.create(BreakingBadService::class.java)

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        db = Room.databaseBuilder(this,
            AppDatabase::class.java, DATABASE_NAME)
            .build()

        repository = CharacterRepository()
        repository.syncCharacterNow()
        repository.scheduleCharactersSync()


//        val character = breakingBadService.getCharacterDetails(1)
//
//        character.enqueue(object: Callback<List<Character>> {
//            override fun onResponse(
//                call: Call<List<Character>>,
//                response: Response<List<Character>>
//            ) {
//                Timber.e("${call.request()}")
//                Timber.e("${response.body()}")
//            }
//
//            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
//
//                Timber.e("${t.message}")
//                Timber.e("FAILLLLL")
//            }
//
//        })
    }
}