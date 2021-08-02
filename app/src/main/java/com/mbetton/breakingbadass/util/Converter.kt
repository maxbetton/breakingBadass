package com.mbetton.breakingbadass.util

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken




class Converter {

    @TypeConverter
    fun fromString(value: String): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<String>): String {
        return Gson().toJson(someObjects)
    }
    @TypeConverter
    fun listToInt(someObjects: List<Int>): String {
        return Gson().toJson(someObjects)
    }

    @TypeConverter
    fun fromInt(value: String): ArrayList<Int> {
        val listType = object : TypeToken<ArrayList<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }
}