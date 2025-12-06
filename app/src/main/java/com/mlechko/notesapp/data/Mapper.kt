package com.mlechko.notesapp.data

import com.mlechko.notesapp.domain.Note

fun Note.toDbModel(): NoteDbModel {
    return NoteDbModel(id = this.id, title = this.title, content = this.content, isPinned = this.isPinned, updatedAt = this.updatedAt)
}

fun NoteDbModel.toEntity(): Note {
    return Note(id = this.id, title = this.title, content = this.content, isPinned = this.isPinned, updatedAt = this.updatedAt)
}

fun List<NoteDbModel>.toEntities(): List<Note> {
    return map { it.toEntity() }
}