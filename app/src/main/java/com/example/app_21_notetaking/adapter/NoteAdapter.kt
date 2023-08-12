package com.example.app_21_notetaking.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app_21_notetaking.databinding.NotesBinding
import com.example.app_21_notetaking.fragment.NotesHomeFragmentDirections
import com.example.app_21_notetaking.model.Note
import java.util.Random

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {


    class MyViewHolder(val binding: NotesBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.body == newItem.body
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            NotesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.binding.noteTitle.text = currentNote.title
        holder.binding.noteBody.text = currentNote.body


        val color =
            Color.argb(255, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))

        holder.binding.view1.setBackgroundColor(color)

        holder.itemView.setOnClickListener {
            val direction = NotesHomeFragmentDirections.actionNotesHomeFragmentToUpdateNoteFragment(currentNote)
            it.findNavController().navigate(direction)
        }

    }


}