package com.mbetton.breakingbadass.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mbetton.breakingbadass.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    fun getAllCharacters() : LiveData<List<Character>>

    @Query("SELECT * FROM character WHERE char_id = :char_id")
    fun getCharacterById(char_id: Int) : LiveData<Character>

    @Insert
    fun insertCharacters(characters: List<Character>)
}