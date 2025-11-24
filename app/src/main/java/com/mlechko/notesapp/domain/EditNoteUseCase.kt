package com.mlechko.notesapp.domain

class EditNoteUseCase(
    private val repository: NotesRepository
) {

    operator suspend fun invoke(note: Note) {
        repository.editNote(note.copy(updatedAt = System.currentTimeMillis()))
    }
}