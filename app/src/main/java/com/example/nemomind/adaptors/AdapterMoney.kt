
package com.example.nemomind.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nemomind.databinding.ItemAddNewMoneyBinding
import com.example.nemomind.databinding.ItemManageMoneyBinding
import com.example.nemomind.databinding.ItemNewNoteBinding
import com.example.nemomind.datas.DataManageMoneyeHome
import com.example.nemomind.datas.DataMoney
import com.example.nemomind.datas.DataNote

class AdapterMoney(val dataMoney: ArrayList<DataMoney>, val moneyEvent: MoneyEvent) : RecyclerView.Adapter<AdapterMoney.ViewHolder>() {
    lateinit var binding: ItemAddNewMoneyBinding

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindViews(position: Int){
            binding.txtNewMoneySubject.text = dataMoney[position].subject_money
            binding.txtNewMoneyCostOrIncome.text = dataMoney[position].cost_or_income_money
            binding.txtNewMoneyCost.text = dataMoney[position].cost_money
            binding.txtNewMoneyDate.text = dataMoney[position].data_money
            binding.txtNewMoneyTime.text = dataMoney[position].time_money


            view.setOnClickListener{
                moneyEvent.onClickMoney(dataMoney[adapterPosition] , adapterPosition , binding.updateNewMoney , binding.txtNewMoneyCostOrIncome )


            }
            moneyEvent.finishDeleting(binding.txtNewMoneyCostOrIncome , binding.txtNewMoneyCost.text.toString())
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemAddNewMoneyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }
    override fun getItemCount(): Int {
        return dataMoney.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(position)

    }
    fun addNewMoney(Money: DataMoney){
        dataMoney.add(0 , Money)
        notifyItemInserted(0)
    }
    fun deleteMoney(Money: Int){
        dataMoney.removeAt(Money)
        notifyDataSetChanged()
    }
    fun updateMoney(Money: DataMoney , position: Int){

        dataMoney.set(position , Money)
        notifyItemChanged(position)



    }

    interface MoneyEvent{

        fun onClickMoney(money: DataMoney , position: Int, updatMoney:TextView , txtIncomeOrCost:TextView )
        fun finishDeleting( txtIncomeOrCost:TextView , textCost:String)
    }





}



