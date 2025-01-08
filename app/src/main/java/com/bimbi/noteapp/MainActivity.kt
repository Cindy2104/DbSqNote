package com.bimbi.noteapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bimbi.noteapp.adapter.NotesAdapter
import com.bimbi.noteapp.databinding.ActivityMainBinding
import com.bimbi.noteapp.helper.NoteDatabaseHelper
import com.bimbi.noteapp.screen.AddNoteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var db : NoteDatabaseHelper
    //privat lateinit var addBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        notesAdapter = NotesAdapter(db.getAllNotes(), this)

        binding.notesRecycleview.layoutManager = LinearLayoutManager(this)
        binding.notesRecycleview.adapter = notesAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    //TASK
    //Ketika item list note di  klik dia akan ke page detail sesuai dengan item dan content nya
    override fun onResume() {
        super.onResume()
        val notes = db.getAllNotes()
        notesAdapter.refreshData(notes)
    }
    //    enableEdgeToEdge()
    //    setContentView(R.layout.activity_main)
        //addbtn = findviewByid
        //addbtn.setonClick
    //   ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
    //        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
    //        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
    //        insets
    //    }
    }
