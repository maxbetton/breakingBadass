package com.mbetton.breakingbadass.repository

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mbetton.breakingbadass.App
import com.mbetton.breakingbadass.Character
import com.mbetton.breakingbadass.breakingbadapi.BreakingBadService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class SyncRepositoryWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.breakingbadapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val breakingBadService: BreakingBadService = retrofit.create(BreakingBadService::class.java)
    private val disposable = CompositeDisposable()

    val characterDao = App.db.characterDao()

    override fun doWork(): Result {
        Timber.e("Synchronizing Characters")
        disposable.add(
            breakingBadService.getAllCharacters().doOnNext { response -> onResponse(response) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) })
        )
        return Result.success()
    }

    private fun onResponse(response: List<Character>) {
        characterDao.insertCharacters(response)
    }

    private fun onFailure(t: Throwable) {
    }
}