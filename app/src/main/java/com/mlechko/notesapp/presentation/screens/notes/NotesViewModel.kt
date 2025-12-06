@file:OptIn(ExperimentalCoroutinesApi::class)

package com.mlechko.notesapp.presentation.screens.notes

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mlechko.notesapp.data.NotesRepositoryImpl
import com.mlechko.notesapp.domain.GetAllNotesUseCase
import com.mlechko.notesapp.domain.Note
import com.mlechko.notesapp.domain.SearchNotesUseCase
import com.mlechko.notesapp.domain.SwitchPinnedStatusUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(context: Context): ViewModel() {

    private val repository = NotesRepositoryImpl.getInstance(context)

    private val getAllNotes = GetAllNotesUseCase(repository)
    private val searchNotes = SearchNotesUseCase(repository)

    private val switchPinnedStatus = SwitchPinnedStatusUseCase(repository)
    private val _state = MutableStateFlow<NotesScreenState>(NotesScreenState())
    val state = _state.asStateFlow()

    private val query = MutableStateFlow<String>("")

    init {

        query
            .onEach { query ->
                _state.update { it.copy(query = query) }
            }
            .flatMapLatest {query ->
                if (query.isBlank()) {
                    getAllNotes()
                } else {
                    searchNotes(query)
                }
            }
            .onEach { notes ->
                val pinnedNotes = notes.filter { it.isPinned }
                val otherNotes = notes.filter { !it.isPinned }
                _state.update { it.copy(pinnedNotes = pinnedNotes, otherNotes = otherNotes) }
            }
            .launchIn(viewModelScope)
    }

    fun processCommands(command: NotesScreenCommand){
        viewModelScope.launch {
            when (command) {

                is NotesScreenCommand.InputSearchQuery -> {
                    query.update { command.query.trim() }
                }

                is NotesScreenCommand.SwitchPinnedStatus -> {
                    switchPinnedStatus(command.noteId)
                }
            }
        }
    }

}

sealed interface NotesScreenCommand{

    data class InputSearchQuery(val query: String): NotesScreenCommand

    data class SwitchPinnedStatus(val noteId: Int): NotesScreenCommand
}
data class NotesScreenState(
    val pinnedNotes: List<Note> = listOf(),
    val otherNotes: List<Note> = listOf(),
    val query: String = ""
)
