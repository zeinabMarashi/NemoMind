
package com.example.nemomind.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nemomind.databinding.ItemNewNoteBinding
import com.example.nemomind.datas.DataNote

class AdapterNote(val dataNote: ArrayList<DataNote>,  val noteEvent: NoteEvent) : RecyclerView.Adapter<AdapterNote.ViewHolder>() {
    lateinit var binding: ItemNewNoteBinding

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindViews(position: Int){
            binding.txtShowNote.text = dataNote[position].note


            view.setOnClickListener{
               noteEvent.onClickNote(dataNote[adapterPosition] , adapterPosition)


            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemNewNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }
    override fun getItemCount(): Int {
        return dataNote.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(position)

    }
    fun addNewNote(note: DataNote){
        dataNote.add(0 , note)
        notifyItemInserted(0)
    }
    fun deleteNote(note: Int){
        dataNote.removeAt(note)
        notifyDataSetChanged()
    }
    fun updateNote(note: DataNote , position: Int){

        dataNote.set(position , note)
        notifyItemChanged(position)



    }

    interface NoteEvent{

        fun onClickNote(note: DataNote , position: Int)
    }




}



