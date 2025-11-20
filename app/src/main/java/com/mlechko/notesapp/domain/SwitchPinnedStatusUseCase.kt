package com.mlechko.notesapp.domain

class SwitchPinnedStatusUseCase(
    private val repository: NotesRepository
) {

    operator fun invoke(noteId: Int) {
        repository.switchPinnedStatus(noteId)
    }
}