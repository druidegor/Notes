package com.mlechko.notesapp.domain

data class Note (
    val id: Int,
    val title: String,
    val content: String,
    val isPinned: Boolean,
    val updatedAt: Long
)