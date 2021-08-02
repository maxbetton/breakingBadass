package com.mbetton.breakingbadass.characterslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mbetton.breakingbadass.Character
import com.mbetton.breakingbadass.R
import com.squareup.picasso.Picasso

class CharactersListAdapter(private val characters: List<Character>,
                            private val listener : CharactersListAdapterListener?) : RecyclerView.Adapter<CharactersListAdapter.ViewHolder>(),
    View.OnClickListener {

    interface CharactersListAdapterListener {
        fun onCharacterSelected(character: Character)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cardView = itemView.findViewById<CardView>(R.id.cardView)
        val characterCover = itemView.findViewById<ImageView>(R.id.characterCover)
        val characterName = itemView.findViewById<TextView>(R.id.characterName)
        val characterNickname = itemView.findViewById<TextView>(R.id.characterNickname)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        with(holder) {
            cardView.setOnClickListener(this@CharactersListAdapter)
            cardView.tag = character
            characterName.text = character.name
            characterNickname.text = character.nickname

            Picasso.get()
                .load(character.img)
                .into(characterCover)
        }

    }

    override fun getItemCount(): Int = characters.size

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cardView -> listener?.onCharacterSelected(v.tag as Character)
        }

    }
}