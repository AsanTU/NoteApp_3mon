package com.example.noteapp_3mon.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noteapp_3mon.R
import androidx.core.content.edit
import com.example.noteapp_3mon.ui.fragments.notes.NotesFragment
import com.example.noteapp_3mon.ui.fragments.onboard.OnBoardFragment

class MainActivity : AppCompatActivity() {

    private val PREFS_NAME = "onboard_prefs"
    private val ONBOARD_SHOWN_KEY = "onboard_shown"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (!isOnBoardShown()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, OnBoardFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NotesFragment())
                .commit()
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