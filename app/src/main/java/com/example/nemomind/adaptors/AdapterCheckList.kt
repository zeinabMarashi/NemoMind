
package com.example.nemomind.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nemomind.datas.WorkData
import com.example.nemomind.databinding.ItemCheckListBinding


class AdapterCheckList(val dataWork: ArrayList<WorkData>, private val workEvent: WorkEvent) : RecyclerView.Adapter<AdapterCheckList.ViewHolder>() {
    lateinit var binding: ItemCheckListBinding

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindViews(position: Int){
            binding.txtCheckListTime.text = dataWork[position].txt_check_list_time
            binding.txtCheckListDate.text = dataWork[position].txt_check_list_date
            binding.txtCheckListGroup.text = dataWork[position].txt_check_list_group
            binding.txtCheckListSubject.text = dataWork[position].txt_check_list_subject
            binding.txtCheckListSubtitle.text = dataWork[position].txt_check_list_subtitle



            view.setOnClickListener{
                workEvent.onClickWork(dataWork[adapterPosition] , adapterPosition)


            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCheckListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return dataWork.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(position)

    }
    fun addNewWord(work: WorkData){
        dataWork.add(0 , work)
        notifyItemInserted(0)
    }

    fun deleteWork(work: Int){
        dataWork.removeAt(work)
        notifyDataSetChanged()
    }

    fun updateWork (work: WorkData, position: Int){

        dataWork.set(position , work)
        notifyItemChanged(position)



    }

    interface WorkEvent{

        fun onClickWork(work: WorkData, position: Int)
    }




}



