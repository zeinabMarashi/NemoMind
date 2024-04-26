package com.example.nemomind.fragmentsAll

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nemomind.SwipeToDeleteCallBack
import com.example.nemomind.adaptors.AdapterNote
import com.example.nemomind.databinding.DialogEditNoteBinding
import com.example.nemomind.databinding.DialogNewNoteBinding
import com.example.nemomind.databinding.FragmentNoteBinding
import com.example.nemomind.datas.DataNote
import com.example.nemomind.datas.WorkData

class Note : Fragment(), AdapterNote.NoteEvent {
    private lateinit var binding: FragmentNoteBinding
    private lateinit var adapterNote: AdapterNote

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        showAllData()
        addNote()
        deleteNote()
    }

    override fun onClickNote(note: DataNote, position: Int) {
        val dialogEditNote = AlertDialog.Builder(requireContext()).create()
        val viewEdit = DialogEditNoteBinding.inflate(layoutInflater)
        viewEdit.editeNote.setText(note.note)
        dialogEditNote.setView(viewEdit.root)
        dialogEditNote.setCancelable(true)
        dialogEditNote.show()

        viewEdit.btnCancel.setOnClickListener {
            dialogEditNote.dismiss()
        }

        viewEdit.btnSaveNot.setOnClickListener {

            if (viewEdit.editeNote.length() > 0) {
                val text = viewEdit.editeNote.text.toString()
                val newNote = DataNote(note = text)

                adapterNote.updateNote(newNote, position)
                dialogEditNote.dismiss()
            } else {

                Toast.makeText(requireContext(), "لطفا نوشته خود را وارد کنید", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


    private fun showAllData() {

        val dataNote = listOf<DataNote>()
        adapterNote = AdapterNote(ArrayList(dataNote), this)
        binding.recyclerNote.adapter = adapterNote
        binding.recyclerNote.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun addNote() {
        binding.addNewNote.setOnClickListener {
            val dialogAddNote = AlertDialog.Builder(requireContext()).create()
            val dialogBinding = DialogNewNoteBinding.inflate(layoutInflater)
            dialogAddNote.setView(dialogBinding.root)
            dialogAddNote.setCancelable(true)
            dialogAddNote.show()

            dialogBinding.btnSaveText.setOnClickListener {
                if (dialogBinding.newNote.text!!.isNotEmpty()) {
                    val note = dialogBinding.newNote.text.toString()
                    val newNote = DataNote(note = note)
                    adapterNote.addNewNote(newNote)
                    dialogAddNote.dismiss()
                } else {
                    Toast.makeText(requireContext(), "لطفا متن خود را وارد کنید", Toast.LENGTH_LONG)
                        .show()
                }
            }
            dialogBinding.btnCancel.setOnClickListener {
                dialogAddNote.dismiss()
            }
        }
    }

    private fun deleteNote() {
        val swipe = object : SwipeToDeleteCallBack(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        adapterNote.deleteNote(viewHolder.adapterPosition)
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(binding.recyclerNote)
    }
}




