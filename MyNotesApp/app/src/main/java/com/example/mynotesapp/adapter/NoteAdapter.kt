package com.example.mynotesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mynotesapp.R
import com.example.mynotesapp.databinding.ItemNoteBinding
import com.example.mynotesapp.entity.Note

class NoteAdapter(private val onItemClickCallback: OnItemClickCallback) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    var listNotes = ArrayList<Note>()
        set(listNote) {
            if (listNote.size > 0){
                this.listNotes.clear()
            }
            this.listNotes.addAll(listNote)
        }

    inner class NoteViewHolder(item: View) : ViewHolder(item) {
        private val binding = ItemNoteBinding.bind(item)
        fun bind(note: Note){
            binding.tvItemTitle.text = note.title
            binding.tvItemDate.text = note.date
            binding.tvItemDescription.text = note.description
            binding.cvItemNote.setOnClickListener {
                onItemClickCallback.onItemClicked(note, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.listNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    interface OnItemClickCallback{
        fun onItemClicked(selectedNote: Note?, position: Int?)
    }

    fun addItem(note: Note){
        this.listNotes.add(note)
        notifyItemInserted(this.listNotes.size - 1)
    }

    fun updateitem(position: Int, note: Note){
        this.listNotes[position] = note
        notifyItemChanged(position, note)
    }

    fun removeItem(position: Int){
        this.listNotes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listNotes.size)
    }


}