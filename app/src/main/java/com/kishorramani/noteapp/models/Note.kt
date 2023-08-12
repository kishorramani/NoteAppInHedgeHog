package com.kishorramani.noteapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "note_table")
data class Note(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        @ColumnInfo(name = "text")
        val text: String,
        val createdDate: Date,
        val isCompleted: Int,    //0 for  not complete, 1 for complete
        val isImportant: Int
)
