package com.mbetton.breakingbadass.breakingbadapi

import com.mbetton.breakingbadass.Character
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BreakingBadService {

    @GET("characters")
    fun getAllCharacters(): Observable<List<Character>>

    @GET("characters/{id}")
    fun getCharacterDetails(@Path("id")characterId: Int): Call<Character>
}