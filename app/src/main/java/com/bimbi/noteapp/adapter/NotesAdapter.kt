package com.bimbi.noteapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bimbi.noteapp.R
import com.bimbi.noteapp.helper.NoteDatabaseHelper
import com.bimbi.noteapp.model.ModelNote

class NotesAdapter(
    private var notes: List<ModelNote>,
    context: Context
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder> (){
    private  val  db : NoteDatabaseHelper = NoteDatabaseHelper(context)
    class NoteViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val txtItemTitle :TextView = itemView.findViewById(R.id.txtItemJudul)
        val txtItemContent :TextView = itemView.findViewById(R.id.txtItemIsiNotes)
        val btnEdit :ImageView = itemView.findViewById(R.id.btnEdit)
        val btnDelete :ImageView = itemView.findViewById(R.id.btnDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note,
            parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesAdapter.NoteViewHolder, position: Int) {
        val noteData = notes[position]
        holder.txtItemTitle.text = noteData.title
        holder.txtItemContent.text = noteData.content

        holder.btnDelete.setOnClickListener(){
            AlertDialog.Builder(holder.itemView.context) .apply {
                setTitle("Confirmation")
                setMessage("Do you want to continue ?")
                setIcon(R.drawable.baseline_delete_24)

                setPositiveButton("Yes"){
                    dialogInterface, i->
                    db.deleteNote(noteData.id)
                    refreshData(db.getAllNotes())
                    Toast.makeText(holder.itemView.context, "Note Berhasil di Hapus",
                        Toast.LENGTH_SHORT).show()
                    dialogInterface.dismiss()
                }
                setNeutralButton( "No"){
                    dialogInterface, i->
                    dialogInterface.dismiss()
                }
            }.show() //untuk menampilkan alert dialog
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }


    //fitur untuk auto refresh data
    fun refreshData(newNotes: List<ModelNote>){
        notes = newNotes
        notifyDataSetChanged()
    }
}