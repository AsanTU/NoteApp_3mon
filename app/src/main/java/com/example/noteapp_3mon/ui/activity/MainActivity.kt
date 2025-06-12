package com.example.noteapp_3mon.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noteapp_3mon.R
import androidx.core.content.edit
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.noteapp_3mon.ui.fragments.notes.NotesFragment
import com.example.noteapp_3mon.ui.fragments.onboard.OnBoardFragment

class MainActivity : AppCompatActivity() {

    private val PREFS_NAME = "onboard_prefs"
    private val ONBOARD_SHOWN_KEY = "onboard_shown"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (isOnBoardShown()) {
            navController.navigate(R.id.notesFragment)
        }
    }

    fun isOnBoardShown(): Boolean{
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        return prefs.getBoolean(ONBOARD_SHOWN_KEY, false)
    }

    fun setOnBoardShown() {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        prefs.edit() { putBoolean(ONBOARD_SHOWN_KEY, true) }
    }
}