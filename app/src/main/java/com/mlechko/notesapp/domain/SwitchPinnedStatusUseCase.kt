package com.mlechko.notesapp.domain

class SwitchPinnedStatusUseCase(
    private val repository: NotesRepository
) {

    operator suspend fun invoke(noteId: Int) {
        repository.switchPinnedStatus(noteId)
    }
}