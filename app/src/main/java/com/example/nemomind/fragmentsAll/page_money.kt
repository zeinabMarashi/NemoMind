package com.example.nemomind.fragmentsAll

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nemomind.SwipeToDeleteCallBack
import com.example.nemomind.adaptors.AdapterManageMoneyHome
import com.example.nemomind.adaptors.AdapterMoney
import com.example.nemomind.databinding.DialogAddNewMoneyBinding
import com.example.nemomind.databinding.DialogAddNewMoneyHomeBinding
import com.example.nemomind.databinding.FragmentPageMoneyBinding
import com.example.nemomind.datas.DataManageMoneyeHome
import com.example.nemomind.datas.DataMoney
import kotlin.properties.Delegates

class page_money : Fragment(), AdapterMoney.MoneyEvent , AdapterManageMoneyHome.ManageMoneyPostEvent {
    private var rest:Int = 0
    private var total_cost:Int= 0
    private var total_income:Int= 0
    lateinit var binding: FragmentPageMoneyBinding
    lateinit var adapterMoney: AdapterMoney
    lateinit var costOrIncome: String
    private var deletedOrNo by Delegates.notNull<Boolean>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageMoneyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        showAllData()
        addMoney()
        deleteMoney()









    }

    private fun showAllData() {
        val dataMoney = listOf<DataMoney>()
        adapterMoney = AdapterMoney(ArrayList(dataMoney), this)
        binding.recyclerMoney.adapter = adapterMoney
        binding.recyclerMoney.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    }

    fun isAlphabetic(input: String): Boolean {
        return input.matches(Regex("[\\p{Alpha}\\p{Punct}]+"))
    }
    fun isNumeric(input: String): Boolean {
        return input.matches(Regex("[\\d\\p{Punct}]+"))
    }

    private fun addMoney() {

        binding.btnAddNewMoney.setOnClickListener {
            val dialogAddMoney = AlertDialog.Builder(requireContext()).create()
            val dialogBinding = DialogAddNewMoneyBinding.inflate(layoutInflater)
            dialogAddMoney.setView(dialogBinding.root)
            dialogAddMoney.setCancelable(true)
            dialogAddMoney.show()



            dialogBinding.btnSaveNewMoney.setOnClickListener {
                val subjectText = dialogBinding.txtNewMoneySubject.text.toString()
                val timeText = dialogBinding.txtNewMoneyTime.text.toString()
                val dataText = dialogBinding.txtNewMoneyDate.text.toString()
                val costText = dialogBinding.txtNewMoneyCost.text.toString()

                if (subjectText.isNotEmpty() && isAlphabetic(subjectText) &&
                    dialogBinding.radioNewMoneyCost.isChecked ||
                    dialogBinding.radioNewMoneyIncome.isChecked &&
                    costText.isNotEmpty() && isNumeric(costText) &&
                    timeText.isNotEmpty() && isNumeric(timeText) &&
                    dataText.isNotEmpty() && isNumeric(dataText)
                ) {

                    if (dialogBinding.radioNewMoneyCost.isChecked) {
                        costOrIncome = "پرداختی"
                    } else {
                        costOrIncome = "دریافتی"

                        val newMoney = DataMoney(subjectText, costOrIncome, costText, dataText, timeText)
                        adapterMoney.addNewMoney(newMoney)
                    }



                    if(dialogBinding.radioNewMoneyCost.isChecked){
                        total_cost += costText.toInt()
                        binding.txtPageMoneyAllCost.text = total_cost.toString()
                    }else{
                        total_income+= costText.toInt()
                        binding.txtPageMoneyAllIncome.text = total_income.toString()

                    }

                    rest = total_income - total_cost
                    binding.txtPageMoneyAllRest.text = rest.toString()

                    dialogAddMoney.dismiss()


                } else {
                    Toast.makeText(requireContext(), "لطفا اطلاعات را وارد کنید", Toast.LENGTH_LONG)
                        .show()
                }

            }
            dialogBinding.btnCancleNewMoney.setOnClickListener {
                dialogAddMoney.dismiss()
            }



        }


    }

    private fun deleteMoney() {

        val swipe = object : SwipeToDeleteCallBack(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        adapterMoney.deleteMoney(viewHolder.adapterPosition)


                    }


                }
            }
        }
        deletedOrNo = true
        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(binding.recyclerMoney)





    }

    override fun finishDeleting(txtIncomeOrCost: TextView, textCost: String) {
        if (deletedOrNo){
            if (txtIncomeOrCost.text == "دریافتی"){
                total_income -= textCost.toInt()
                rest-= textCost.toInt()
            }else{

                total_cost-= textCost.toInt()
                rest+= textCost.toInt()
            }
            deletedOrNo=false
        }
    }


    override fun onClickManageMoneyPost(textCost: TextView, textInCome: TextView, textRest: TextView, position: Int
    ) {
        textCost.text = total_cost.toString()
        textInCome.text = total_income.toString()
        textRest.text = rest.toString()
    }



    override fun onClickMoney(money: DataMoney, position: Int, updatMoney: TextView, txtIncomeOrCost: TextView
    ) {
        //update item money
        updatMoney.setOnClickListener {
            val dialogEditMoney = AlertDialog.Builder(requireContext()).create()
            val viewEdit = DialogAddNewMoneyBinding.inflate(layoutInflater)
            viewEdit.txtNewMoneySubject.setText(money.subject_money)
            viewEdit.txtNewMoneyCost.setText(money.cost_money)
            viewEdit.txtNewMoneyDate.setText(money.data_money)
            viewEdit.txtNewMoneyTime.setText(money.time_money)
            if (txtIncomeOrCost.text== "پرداختی") {
                viewEdit.radioNewMoneyCost.isChecked = true
            } else {
                viewEdit.radioNewMoneyIncome.isChecked = true
            }



            dialogEditMoney.setView(viewEdit.root)
            dialogEditMoney.setCancelable(true)
            dialogEditMoney.show()

            viewEdit.btnCancleNewMoney.setOnClickListener {
                dialogEditMoney.dismiss()
            }

            viewEdit.btnSaveNewMoney.setOnClickListener {

                val subjectEdit = viewEdit.txtNewMoneySubject.text.toString()
                val castEdit = viewEdit.txtNewMoneyCost.text.toString()
                val timeEdit = viewEdit.txtNewMoneyTime.text.toString()
                val dateEdit = viewEdit.txtNewMoneyDate.text.toString()
                if (viewEdit.radioNewMoneyCost.isChecked) {
                    costOrIncome = "پرداختی"
                } else {
                    costOrIncome = "دریافتی"
                }

                if (subjectEdit.isNotEmpty() && isAlphabetic(subjectEdit)&&
                    castEdit.isNotEmpty() && isNumeric(castEdit)&&
                    timeEdit.isNotEmpty() && isNumeric(timeEdit)&&
                    dateEdit.isNotEmpty() && isNumeric(dateEdit)&&
                    viewEdit.radioNewMoneyIncome.isChecked ||
                    viewEdit.radioNewMoneyCost.isChecked
                ) {
                    if(money.cost_or_income_money == "پرداختی"){
                        total_cost - money.cost_money.toInt()
                        rest + money.cost_money.toInt()

                    }else{

                        total_income - money.cost_money.toInt()
                        rest - money.cost_money.toInt()
                    }


                    rest = total_income - total_cost
                    binding.txtPageMoneyAllRest.text = rest.toString()



                    val newMoney = DataMoney(subjectEdit, costOrIncome,castEdit, dateEdit,timeEdit)
                    adapterMoney.updateMoney(newMoney, position)

                    if(viewEdit.radioNewMoneyCost.isChecked){
                        total_cost += castEdit.toInt()
                        binding.txtPageMoneyAllCost.text = total_cost.toString()
                    }else{
                        total_income+= castEdit.toInt()
                        binding.txtPageMoneyAllIncome.text = total_income.toString()

                    }

                    rest = total_income - total_cost
                    binding.txtPageMoneyAllRest.text = rest.toString()


                    dialogEditMoney.dismiss()
                } else {

                    Toast.makeText(
                        requireContext(),
                        "لطفا اطلاعات خود را ویرایش کنید",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }


            }

        }





    }




}