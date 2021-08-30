package com.mbetton.breakingbadass.characterslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mbetton.breakingbadass.App
import com.mbetton.breakingbadass.Character
import timber.log.Timber

class CharactersListViewModel : ViewModel() {

    var characters : LiveData<List<Character>> = App.db.characterDao().getAllCharacters()
}