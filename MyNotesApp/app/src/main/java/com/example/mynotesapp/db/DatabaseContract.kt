package com.example.mynotesapp.db

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class NoteColums : BaseColumns{
        companion object{
            // key kolom
            const val TABLE_NAME = "note"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val DATE = "date"
        }
    }
}