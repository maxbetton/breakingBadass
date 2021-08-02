package com.mbetton.breakingbadass.repository

import androidx.work.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class CharacterRepository {

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    fun syncCharacterNow() {
        Timber.e("Synchronizing characters now")
        val work = OneTimeWorkRequest.Builder(SyncRepositoryWorker::class.java)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance()
            .beginUniqueWork("syncCaracterNow", ExistingWorkPolicy.KEEP, work)
            .enqueue()
    }

    fun scheduleCharactersSync() {
        Timber.e("Synchronizing characters every 12 hours")
        val work = PeriodicWorkRequest.Builder(SyncRepositoryWorker::class.java, 12, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance()
            .enqueueUniquePeriodicWork("syncCharacterScheduled",
            ExistingPeriodicWorkPolicy.KEEP,
            work)
    }
}