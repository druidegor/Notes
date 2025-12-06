package com.mlechko.notesapp.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mlechko.notesapp.presentation.screens.creating.CreateNoteScreen
import com.mlechko.notesapp.presentation.screens.editing.EditNoteScreen
import com.mlechko.notesapp.presentation.screens.notes.NotesScreen

@Composable
fun NavGraph(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Notes.route
    ) {
        composable(Screen.Notes.route) {
            NotesScreen(
                onNoteClick = {
                    navController.navigate(Screen.EditNote.createRoute(it.id))
                },
                onAddNoteClick = {
                    navController.navigate(Screen.CreateNote.route)
                }
            )
        }

        composable(Screen.CreateNote.route) {
            CreateNoteScreen(
                onFinished = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.EditNote.route) {
            EditNoteScreen(
                noteId = Screen.EditNote.getNoteId(it.arguments),
                onFinished = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {

    data object Notes: Screen("notes")

    data object CreateNote: Screen("create_note")

    data object EditNote: Screen("edit_note/{note_id}") {

        fun createRoute(noteId: Int): String{
            return "edit_note/${noteId}"
        }

        fun getNoteId(arguments: Bundle?): Int {
            return arguments?.getString("note_id")?.toInt() ?: 0
        }
    }

}