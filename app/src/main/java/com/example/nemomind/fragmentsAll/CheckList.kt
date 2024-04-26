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
import com.example.nemomind.adaptors.AdapterCheckList
import com.example.nemomind.databinding.FragmentBottomSheetBinding
import com.example.nemomind.databinding.FragmentCheckListBinding
import com.example.nemomind.datas.WorkData

class CheckList : Fragment(), AdapterCheckList.WorkEvent {
    lateinit var binding: FragmentCheckListBinding
    lateinit var adaptorCheckList: AdapterCheckList


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showAllData()

        binding.lottieWork.playAnimation()


        deleteWork()
        addNewWork()

    }

    private fun deleteWork() {
        val swipe = object : SwipeToDeleteCallBack(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        adaptorCheckList.deleteWork(viewHolder.adapterPosition)

                    }


                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(binding.recyclerCheckList)

    }

    private fun addNewWork() {
        binding.addNewWork.setOnClickListener {



            val mainDialog = androidx.appcompat.app.AlertDialog.Builder(requireContext()).create()
            val dialogBinding = FragmentBottomSheetBinding.inflate(layoutInflater)
            mainDialog.setView(dialogBinding.root)
            mainDialog.setCancelable(true)
            mainDialog.show()



            dialogBinding.btnSave.setOnClickListener {
                Toast.makeText(requireContext(), "sajdhkashdajgdfuhwegydf", Toast.LENGTH_SHORT)
                    .show()

                if (dialogBinding.txtRecordSubject.text!!.isNotEmpty() &&
                    dialogBinding.txtRecordSubtitle.text!!.isNotEmpty() &&
                    dialogBinding.txtRecordDate.text!!.isNotEmpty() &&
                    dialogBinding.txtRecordTime.text!!.isNotEmpty()

                ) {
                    val subject = dialogBinding.txtRecordSubject.text.toString()
                    val subtitle = dialogBinding.txtRecordSubtitle.text.toString()
                    val date = dialogBinding.txtRecordDate.text.toString()
                    val time = dialogBinding.txtRecordTime.text.toString()


                    val newWork = WorkData(
                        txt_check_list_subject = subject,
                        txt_check_list_subtitle = subtitle,
                        txt_check_list_date = date,
                        txt_check_list_time = time,
                        txt_check_list_group = ""
                    )
                    adaptorCheckList.addNewWord(newWork)
                    mainDialog.dismiss()


                } else {
                    Toast.makeText(requireContext(), "لطفا متن خود را وارد کنید", Toast.LENGTH_LONG)
                        .show()
                }

            }
            dialogBinding.btnCancle.setOnClickListener {
                mainDialog.dismiss()
            }
        }

    }

    override fun onClickWork(work: WorkData, position: Int) {
        val dialog = AlertDialog.Builder(requireContext()).create()
        val viewEdit = FragmentBottomSheetBinding.inflate(layoutInflater)
        dialog.setView(viewEdit.root)
        dialog.setCancelable(true)
        dialog.show()
        viewEdit.txtRecordSubject.setText(work.txt_check_list_subject)
        viewEdit.txtRecordSubtitle.setText(work.txt_check_list_subtitle)
        viewEdit.txtRecordDate.setText(work.txt_check_list_date)
        viewEdit.txtRecordTime.setText(work.txt_check_list_time)




        viewEdit.btnCancle.setOnClickListener {
            dialog.dismiss()
        }

        viewEdit.btnSave.setOnClickListener {

            if (viewEdit.txtRecordSubject.length() > 0 &&
                viewEdit.txtRecordSubtitle.length() > 0 &&
                viewEdit.txtRecordDate.length() > 0 &&
                viewEdit.txtRecordTime.length() > 0

            ) {
                val subject = viewEdit.txtRecordSubject.text.toString()
                val subtitle = viewEdit.txtRecordSubtitle.text.toString()
                val date = viewEdit.txtRecordDate.text.toString()
                val time = viewEdit.txtRecordTime.text.toString()


                val newWork = WorkData(
                    id = work.id,
                    txt_check_list_subject = subject,
                    txt_check_list_subtitle = subtitle,
                    txt_check_list_date = date,
                    txt_check_list_time = time,
                    txt_check_list_group = ""
                )


                adaptorCheckList.updateWork(newWork, position)
                dialog.dismiss()
            } else {

                Toast.makeText(
                    requireContext(),
                    "لطفا نوشته خود را وارد کنید",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }

    }

    private fun showAllData() {
        val workData = listOf<WorkData>()
        adaptorCheckList = AdapterCheckList(ArrayList(workData), this)
        binding.recyclerCheckList.adapter = adaptorCheckList
        binding.recyclerCheckList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    }


}