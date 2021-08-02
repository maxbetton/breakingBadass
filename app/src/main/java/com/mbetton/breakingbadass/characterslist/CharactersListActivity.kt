package com.mbetton.breakingbadass.characterslist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbetton.breakingbadass.Character
import com.mbetton.breakingbadass.R
import com.mbetton.breakingbadass.characterdetail.CharacterDetailActivity
import kotlinx.android.synthetic.main.activity_characters_list.*
import timber.log.Timber

class CharactersListActivity : AppCompatActivity(),
    CharactersListAdapter.CharactersListAdapterListener {

    private lateinit var viewModel: CharactersListViewModel
    private lateinit var charactersListAdapter: CharactersListAdapter
    lateinit var characters: MutableList<Character>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters_list)

        characters = mutableListOf()
        charactersListAdapter = CharactersListAdapter(characters, this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CharactersListActivity)
            adapter = charactersListAdapter
        }


        viewModel = ViewModelProvider(this).get(CharactersListViewModel::class.java)

        viewModel.characters.observe(this, { newCharacters -> updateCharacters(newCharacters!!) })
    }

    private fun updateCharacters(newCharacters: List<Character>) {
        Timber.e("List of characters")
        characters.clear()
        characters.addAll(newCharacters)
        charactersListAdapter.notifyDataSetChanged()
    }

    override fun onCharacterSelected(character: Character) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra(CharacterDetailActivity.EXTRA_CHARACTER_ID, character.char_id)
        startActivity(intent)
    }

}