package com.kishorramani.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kishorramani.noteapp.databinding.ActivityMainBinding
import com.kishorramani.noteapp.models.Note
import java.util.Date

//Create Entity [Note][Table]
//Create Dao    [NoteDao][Table query]
//Database      [NoteDatabase][Create room database]
class MainActivity : AppCompatActivity(), INotesRVAdapter {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        binding.recyclerview.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
        viewModel.allNotes.observe(this) { list ->
            adapter.updateList(list)
        }

        binding.btnSave.setOnClickListener {
            val noteText = binding.edtNote.text.toString()
            if (noteText.isNotEmpty()) {
                viewModel.insertNote(Note(0, noteText, Date(), 0, 0))
                Toast.makeText(this, "$noteText Inserted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} Deleted", Toast.LENGTH_SHORT).show()
    }
}