package com.mlechko.notesapp.presentation.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mlechko.notesapp.domain.Note

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = viewModel(),
    onNoteClick: (Note) -> Unit
) {

    val state by viewModel.state.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    state.pinnedNotes.forEach {note ->
                        NoteCard(
                            note = note,
                            backgroundColor = Color.Green,
                            onNoteClick = onNoteClick,
                            onLongClick = {
                                viewModel.processCommands(NotesScreenCommand.SwitchPinnedStatus(note.id))
                            }
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            state.otherNotes.forEach {note ->
                NoteCard(
                    modifier.fillMaxWidth(),
                    note = note,
                    backgroundColor = Color.Yellow,
                    onNoteClick = onNoteClick,
                    onLongClick = {
                        viewModel.processCommands(NotesScreenCommand.SwitchPinnedStatus(note.id))
                    }
                )
            }
        }
    }
}

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note,
    backgroundColor: Color,
    onNoteClick: (Note) -> Unit,
    onLongClick: (Note) -> Unit
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = backgroundColor)
            .combinedClickable(
                onClick ={
                    onNoteClick(note)
                } ,
                onLongClick = {
                    onLongClick(note)
                }
            )
    ) {
        Text(
            text = note.title,
            fontSize = 14.sp,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = note.updatedAt.toString(),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = note.content,
            fontSize = 16.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium
        )
    }
}