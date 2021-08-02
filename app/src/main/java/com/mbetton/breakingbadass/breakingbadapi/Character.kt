package com.mbetton.breakingbadass

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mbetton.breakingbadass.util.Converter

@Entity(tableName = "character")
@TypeConverters(Converter::class)
data class Character(
    @PrimaryKey(autoGenerate = true)
    var char_id: Int,
    var name: String,
    var birthday: String,
    var occupation: List<String>,
    var img: String,
    var status: String,
    var nickname: String,
    var appearance: List<Int>,
    var portrayed: String,
    var category: String
)