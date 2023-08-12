package com.kishorramani.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kishorramani.noteapp.models.Note

class NotesRVAdapter(private val context: Context, private val listener: INotesRVAdapter) : RecyclerView.Adapter<NotesRVAdapter.NotesViewHolder>() {

    private val allNotes = ArrayList<Note>()

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtItem: TextView = itemView.findViewById(R.id.txtNote)
        val txtItemDateCreated: TextView = itemView.findViewById(R.id.txtItemDateCreated)
        val ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))

        viewHolder.ivDelete.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.txtItem.text = currentNote.text
        holder.txtItemDateCreated.text = currentNote.createdDate.toString()
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface INotesRVAdapter {
    fun onItemClicked(note: Note)
}