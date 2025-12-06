package com.mlechko.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mlechko.notesapp.navigation.NavGraph
import com.mlechko.notesapp.presentation.screens.editing.EditNoteScreen
import com.mlechko.notesapp.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            NotesAppTheme {
                NavGraph()
            }
        }
    }
}
