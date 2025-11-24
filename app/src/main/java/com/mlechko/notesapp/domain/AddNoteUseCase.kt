package com.mlechko.notesapp.domain

class AddNoteUseCase (
    private val repository: NotesRepository
){

    operator suspend fun invoke(
        title: String,
        content: String
    ) {
        repository.addNote(
            title = title,
            content = content,
            isPinned = false,
            updatedAt = System.currentTimeMillis()
        )
    }
}