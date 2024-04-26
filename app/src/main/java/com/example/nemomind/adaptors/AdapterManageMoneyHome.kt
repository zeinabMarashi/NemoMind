package com.example.nemomind.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nemomind.databinding.ItemManageMoneyBinding
import com.example.nemomind.datas.DataManageMoneyeHome


class AdapterManageMoneyHome(
    val dataManageMoneyHome: ArrayList<DataManageMoneyeHome>,
    val manageMoneyHomeEvent: ManageMoneyHomeEvent,
    val manageMoneyPost: ManageMoneyPostEvent

) : RecyclerView.Adapter<AdapterManageMoneyHome.ViewHolder>(){
    lateinit var binding: ItemManageMoneyBinding

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindViews(position: Int) {
            binding.txtMoneyHomeSubject.text = dataManageMoneyHome[position].subject_manage_money_home
            binding.txtMoneyHomeData.text = dataManageMoneyHome[position].data_manage_money_home
            binding.txtMoneyHomeTime.text = dataManageMoneyHome[position].time_manage_money_home


            view.setOnClickListener {
                manageMoneyHomeEvent.onClickManageMoneyHome(
                    dataManageMoneyHome[adapterPosition],
                    adapterPosition
                )


            }
            view.setOnLongClickListener {
                manageMoneyHomeEvent.onLongClickManageMoneyHome(
                    dataManageMoneyHome[adapterPosition],
                    adapterPosition
                )
                true
            }

            manageMoneyPost.onClickManageMoneyPost(binding.txtMoneyHomeCost , binding.txtMoneyHomeIncome , binding.txtMoneyHomeRest, adapterPosition )
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemManageMoneyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return dataManageMoneyHome.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(position)

    }

    fun addNewManageMoney(manageMoney: DataManageMoneyeHome) {
        dataManageMoneyHome.add(0, manageMoney)
        notifyItemInserted(0)
    }

    fun deleteManageMoney(manageMoney: Int) {
        dataManageMoneyHome.removeAt(manageMoney)
        notifyDataSetChanged()
    }

    fun updateManageMoney(manageMoney: DataManageMoneyeHome, position: Int) {

        dataManageMoneyHome.set(position, manageMoney)
        notifyItemChanged(position)


    }

    interface ManageMoneyHomeEvent {

        fun onClickManageMoneyHome(manageMoney: DataManageMoneyeHome, position: Int)
        fun onLongClickManageMoneyHome(manageMoney: DataManageMoneyeHome, position: Int)
    }

    interface ManageMoneyPostEvent {

        fun onClickManageMoneyPost(textCost:TextView,textInCome:TextView,textRest:TextView, position: Int)
    }



}



