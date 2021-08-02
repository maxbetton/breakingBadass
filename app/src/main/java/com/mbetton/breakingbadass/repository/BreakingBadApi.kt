package com.mbetton.breakingbadass.repository

import com.mbetton.breakingbadass.Character
import com.mbetton.breakingbadass.breakingbadapi.BreakingBadService
import com.mbetton.breakingbadass.characterslist.CharactersListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class BreakingBadApi {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.breakingbadapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val breakingBadService: BreakingBadService = retrofit.create(BreakingBadService::class.java)

    val disposable = CompositeDisposable()

    var characters: List<Character> = mutableListOf()
    lateinit var viewModel: CharactersListViewModel

    fun loadCharacters() : List<Character> {
        //characters = character.execute().body()!!
        disposable.add(
            breakingBadService.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) })
        )
        Thread.sleep(5000)
        Timber.e("getcharacters ${characters.size}")
        return characters

    }

    private fun onFailure(t: Throwable) {

    }

    private fun onResponse(response: List<Character>) {
        characters = response
    }
}