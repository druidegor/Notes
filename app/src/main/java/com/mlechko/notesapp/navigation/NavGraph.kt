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
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {

        composable(Screen.CreationScreen.route) {
            CreateNoteScreen {
                navController.popBackStack()
            }
        }

        composable(Screen.EditScreen.route) {
            val noteId = Screen.EditScreen.getNoteId(it.arguments)
            EditNoteScreen(
                noteId = noteId
            ) {
                navController.popBackStack()
            }
        }

        composable(Screen.MainScreen.route) {
            NotesScreen(
                onNoteClick = {
                    navController.navigate(Screen.EditScreen.createRoute(it.id))
                },
                onAddNoteClick = {
                    navController.navigate(Screen.CreationScreen.route)
                }
            )
        }
    }
}

sealed class Screen(val route: String) {

    data object CreationScreen: Screen("create_note")

    data object MainScreen: Screen("notes")

    data object EditScreen: Screen("edit_note/{note_id}") {

        fun createRoute(noteId: Int): String {
            return "edit_note/$noteId"
        }

        fun getNoteId(arguments: Bundle?): Int {
            return arguments?.getString("note_id")?.toInt() ?: 0
        }
    }
}