package com.mlechko.notesapp.domain

class DeleteNoteUseCase(
    private val repository: NotesRepository
) {

    operator suspend fun invoke(noteId: Int) {
        repository.deleteNote(noteId)
    }
}