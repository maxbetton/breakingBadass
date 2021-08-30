package com.mbetton.breakingbadass.characterdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mbetton.breakingbadass.Character
import com.mbetton.breakingbadass.R
import com.mbetton.breakingbadass.databinding.ActivityCharacterDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_detail.*

class CharacterDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CHARACTER_ID = "characterId"
    }

    private lateinit var viewModel: CharacterDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val characterId = intent.getIntExtra(EXTRA_CHARACTER_ID, 1)

        val factory = CharacterDetailViewModelFactory(characterId)
        viewModel = ViewModelProvider(this, factory).get(CharacterDetailViewModel::class.java)
        viewModel.character.observe(this, Observer { character -> updateCharacter(character!!) })

    }

    private fun updateCharacter(character: Character) {
        Picasso.get()
            .load(character.img)
            .into(characterCover)

        characterName.text = character.name
        characterNickname.text = character.nickname
        characterBirthday.text = character.birthday
        characterStatus.text = character.status
    }
}