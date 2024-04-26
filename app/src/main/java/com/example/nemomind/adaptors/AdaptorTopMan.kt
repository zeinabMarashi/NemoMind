package com.example.nemomind.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nemomind.datas.DataEducation
import com.example.nemomind.databinding.ItemHomeTopManBinding
import com.example.nemomind.datas.DataTopMan
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class AdaptorTopMan(val dataTopMan: ArrayList<DataTopMan>, val itemEventsTopMan: ItemEventsTopMan) :
    RecyclerView.Adapter<AdaptorTopMan.ManViweHolder>() {

    lateinit var binding: ItemHomeTopManBinding

    inner class ManViweHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun bindViews(dataTopMan: DataTopMan) {
            binding.txtTopMan.text = dataTopMan.subjectTopMan
            binding.txtSubTopMan.text = dataTopMan.subtitletTopMan
            binding.txtMainTopMan.text = dataTopMan.detailTopMan

            Glide.with(itemView.context).load(dataTopMan.imgHomeTopMan).transform(
                RoundedCornersTransformation(32, 8)
            ).into(binding.imgHomeTopMan)
            itemView.setOnClickListener {
                itemEventsTopMan.onItemClickedTopMan(dataTopMan)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManViweHolder {
        binding = ItemHomeTopManBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ManViweHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return dataTopMan.size
    }

    override fun onBindViewHolder(holder: ManViweHolder, position: Int) {
        holder.bindViews(dataTopMan[position])
    }


    interface ItemEventsTopMan {

        fun onItemClickedTopMan(dataTopMan: DataTopMan)

    }

}