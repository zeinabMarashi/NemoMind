package com.example.nemomind.fragmentsAll

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nemomind.R
import com.example.nemomind.SwipeToDeleteCallBack
import com.example.nemomind.adaptors.AdapterManageMoneyHome
import com.example.nemomind.databinding.DialogAddNewMoneyHomeBinding
import com.example.nemomind.databinding.FragmentManageMoneyBinding
import com.example.nemomind.databinding.FragmentPageMoneyBinding
import com.example.nemomind.databinding.ItemManageMoneyBinding
import com.example.nemomind.datas.DataManageMoneyeHome

class manage_money : Fragment(), AdapterManageMoneyHome.ManageMoneyHomeEvent, AdapterManageMoneyHome.ManageMoneyPostEvent {
    lateinit var binding: FragmentManageMoneyBinding
    lateinit var adapterManageMoneyHome: AdapterManageMoneyHome
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageMoneyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showAllData()
        addManageMoney()
        deleteManageMoney()

        binding.lottieMoney.playAnimation()






    }

    private fun showAllData() {
        val dataManageMoney = listOf<DataManageMoneyeHome>()
        adapterManageMoneyHome = AdapterManageMoneyHome(ArrayList(dataManageMoney), this, this)
        binding.recyclerManageMoneyHome.adapter = adapterManageMoneyHome
        binding.recyclerManageMoneyHome.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }


    fun isAlphabetic(input: String): Boolean {
        return input.matches(Regex("[\\p{Alpha}\\p{Punct}]+"))
    }
    fun isNumeric(input: String): Boolean {
        return input.matches(Regex("[\\d\\p{Punct}]+"))
    }



    private fun addManageMoney() {

        binding.btnAddNewManageMoney.setOnClickListener {
            val dialogAddManageMoney = AlertDialog.Builder(requireContext()).create()
            val dialogBinding = DialogAddNewMoneyHomeBinding.inflate(layoutInflater)
            dialogAddManageMoney.setView(dialogBinding.root)
            dialogAddManageMoney.setCancelable(true)
            dialogAddManageMoney.show()


            dialogBinding.btnSaveNewMoneyHome.setOnClickListener {
                val subjectText = dialogBinding.txtNewMoneySubjectHome.text.toString()
                val timeText = dialogBinding.txtNewMoneyTimeHome.text.toString()
                val dataText = dialogBinding.txtNewMoneyDateHome.text.toString()

                if (subjectText.isNotEmpty() && isAlphabetic(subjectText) &&
                    timeText.isNotEmpty() && isNumeric(timeText) &&
                    dataText.isNotEmpty() && isNumeric(dataText)
                ) {
                    val newManageMoney = DataManageMoneyeHome(subjectText, timeText, dataText)
                    adapterManageMoneyHome.addNewManageMoney(newManageMoney)
                    dialogAddManageMoney.dismiss()
                } else {
                    Toast.makeText(requireContext(), "لطفاً ورودی‌ها را با دقت وارد کنید", Toast.LENGTH_LONG).show()
                }
            }



            dialogBinding.btnCancleNewMoneyHome.setOnClickListener {
                dialogAddManageMoney.dismiss()
            }

        }


    }

    private fun deleteManageMoney() {

        val swipe = object : SwipeToDeleteCallBack(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        adapterManageMoneyHome.deleteManageMoney(viewHolder.adapterPosition)


                    }


                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(binding.recyclerManageMoneyHome)
    }

    override fun onClickManageMoneyHome(manageMoney: DataManageMoneyeHome, position: Int) {
        findNavController().navigate(R.id.action_manage_money_to_page_money)




    }

    override fun onLongClickManageMoneyHome(manageMoney: DataManageMoneyeHome, position: Int) {


            val dialogEditManageMoney = AlertDialog.Builder(requireContext()).create()
            val viewEdit = DialogAddNewMoneyHomeBinding.inflate(layoutInflater)
            viewEdit.txtNewMoneySubjectHome.setText(manageMoney.subject_manage_money_home)
            viewEdit.txtNewMoneyTimeHome.setText(manageMoney.time_manage_money_home)
            viewEdit.txtNewMoneyDateHome.setText(manageMoney.data_manage_money_home)
            dialogEditManageMoney.setView(viewEdit.root)
            dialogEditManageMoney.setCancelable(true)
            dialogEditManageMoney.show()

            viewEdit.btnCancleNewMoneyHome.setOnClickListener {
                dialogEditManageMoney.dismiss()
            }

            viewEdit.btnSaveNewMoneyHome.setOnClickListener {
                val subjectEdit = viewEdit.txtNewMoneySubjectHome.text.toString()
                val timeEdit = viewEdit.txtNewMoneyTimeHome.text.toString()
                val dateEdit = viewEdit.txtNewMoneyDateHome.text.toString()

                if (subjectEdit.isNotEmpty() && isAlphabetic(subjectEdit)&&
                    timeEdit.isNotEmpty() && isNumeric(timeEdit)&&
                    dateEdit.isNotEmpty()&& isNumeric(timeEdit)
                ) {
                    val newManageMoney = DataManageMoneyeHome(subjectEdit , timeEdit , dateEdit )

                    adapterManageMoneyHome.updateManageMoney(newManageMoney , position)
                    dialogEditManageMoney.dismiss()
                } else {

                    Toast.makeText(requireContext(), "لطفا اطلاعات خود را ویرایش کنید", Toast.LENGTH_SHORT)
                        .show()



            }
        }
    }

    override fun onClickManageMoneyPost(
        textCost: TextView,
        textInCome: TextView,
        textRest: TextView,
        position: Int
    ) {

    }


}