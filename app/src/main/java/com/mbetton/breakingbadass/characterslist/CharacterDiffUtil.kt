package com.mbetton.breakingbadass.characterslist

import androidx.recyclerview.widget.DiffUtil
import com.mbetton.breakingbadass.Character

class CharacterDiffUtil(private val oldList: List<Character>,
                        private val newList: List<Character>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].char_id == newList[newItemPosition].char_id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].char_id != newList[newItemPosition].char_id -> {
                false
            }
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }
            oldList[oldItemPosition].birthday != newList[newItemPosition].birthday -> {
                false
            }
            else -> return true
        }
    }
}