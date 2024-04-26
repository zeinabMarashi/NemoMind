package com.example.nemomind.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nemomind.datas.DataEducation
import com.example.nemomind.databinding.ItemHomeEducationBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class AdaptorEducation(val dataEducation: ArrayList<DataEducation>, val itemEventsEducation:ItemEventsEducation)  : RecyclerView.Adapter<AdaptorEducation.MainViweHolder>() {

    lateinit var binding: ItemHomeEducationBinding

    inner class MainViweHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun bindViews(dataEducation: DataEducation) {
            binding.txtHomeEducation.text = dataEducation.subjectEducation
            binding.txtHomeSubEducation.text = dataEducation.subtitletEducation
            binding.txtEducationMain.text = dataEducation.textEducation
            binding.txtUrlEducation.text = dataEducation.urlEducation

            Glide.with(itemView.context).load(dataEducation.imgHomeEducation).transform(
                RoundedCornersTransformation(32, 8)
            ).into(binding.imgHomeEducation)
            itemView.setOnClickListener{
                itemEventsEducation.onItemClickedEducation(dataEducation)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViweHolder {
        binding = ItemHomeEducationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViweHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return dataEducation.size
    }

    override fun onBindViewHolder(holder: MainViweHolder, position: Int) {
        holder.bindViews(dataEducation[position])
    }


    interface ItemEventsEducation {

        fun onItemClickedEducation(dataEducation: DataEducation)

    }

}