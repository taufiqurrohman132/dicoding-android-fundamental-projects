package com.example.mynotesapp.helper

import android.database.Cursor
import com.example.mynotesapp.db.DatabaseContract
import com.example.mynotesapp.entity.Note

object MappingHelper {

    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<Note>{
        val notesList = ArrayList<Note>()

        notesCursor?.apply {
            while (moveToNext()){
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.NoteColums._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.NoteColums.TITLE))
                val description = getString(getColumnIndexOrThrow(DatabaseContract.NoteColums.DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DatabaseContract.NoteColums.DATE))
                notesList.add(Note(id, title, description, date))
            }
        }
        return notesList
    }
}