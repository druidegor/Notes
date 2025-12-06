package com.mlechko.notesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteDbModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String,
    val isPinned: Boolean,
    val updatedAt: Long
)