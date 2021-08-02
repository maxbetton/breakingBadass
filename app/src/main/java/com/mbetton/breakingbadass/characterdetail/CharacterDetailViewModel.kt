package com.mbetton.breakingbadass.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mbetton.breakingbadass.App
import com.mbetton.breakingbadass.Character

class CharacterDetailViewModel(characterId: Int) : ViewModel() {
    private val characterIdLiveData = MutableLiveData<Int>()

    val character: LiveData<Character> = Transformations.switchMap(characterIdLiveData) { id ->
        App.db.characterDao().getCharacterById(id)
    }

    init {
        characterIdLiveData.value = characterId
    }
}