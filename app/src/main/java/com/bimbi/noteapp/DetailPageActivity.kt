package com.bimbi.noteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bimbi.noteapp.databinding.ActivityDetailPageBinding


class DetailNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari Intent
        val noteTitle = intent.getStringExtra("NOTE_TITLE") ?: "No Title"
        val noteContent = intent.getStringExtra("NOTE_CONTENT") ?: "No Content"

        // Tampilkan data di layar
        binding.noteTitle.text = noteTitle
        binding.noteContent.text = noteContent
    }
}