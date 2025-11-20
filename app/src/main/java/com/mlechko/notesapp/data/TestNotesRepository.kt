package com.mlechko.notesapp.data

import androidx.compose.runtime.MutableState
import com.mlechko.notesapp.domain.Note
import com.mlechko.notesapp.domain.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

object TestNotesRepository: NotesRepository {

    private val repository = MutableStateFlow<List<Note>>(listOf())

    override fun addNote(note: Note) {
        repository.update {
            it + note
        }
    }

    override fun deleteNote(noteId: Int) {
        repository.update { previousList ->
            previousList.toMutableList()
                .removeIf {
                    it.id == noteId
                }
            previousList
        }
    }

    override fun editNote(note: Note) {
        repository.update { previousList ->
            previousList.map {
                if (it.id == note.id)
                    note
                else
                    it
            }
        }
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return repository.asStateFlow()
    }

    override fun getNote(noteId: Int): Note {
        return repository.value.first { it.id == noteId }
    }

    override fun searchNotes(query: String): Flow<List<Note>> {
        return repository.map {currentList ->
            currentList.filter {
                it.title.contains(query) || it.content.contains(query)
            }
        }
    }

    override fun switchPinnedStatus(noteId: Int) {
        repository.update { previousList ->
            previousList.map {
                if (it.id == noteId) {
                    it.copy(isPinned = !it.isPinned)
                }
                else {
                    it
                }
            }
        }
    }
}