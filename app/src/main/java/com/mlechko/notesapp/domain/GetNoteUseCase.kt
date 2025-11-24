package com.mlechko.notesapp.domain

class GetNoteUseCase(
    private val repository: NotesRepository
) {

    operator suspend fun invoke(noteId: Int): Note {
        return repository.getNote(noteId)
    }
}