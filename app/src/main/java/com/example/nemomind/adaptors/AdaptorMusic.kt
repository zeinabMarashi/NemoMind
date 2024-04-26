package com.example.nemomind.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nemomind.databinding.ItemHomeMusicBinding
import com.example.nemomind.datas.DataMusic
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class AdaptorMusic(val dataMusic: ArrayList<DataMusic>, val itemEventsMusic:ItemEventsMusic)  : RecyclerView.Adapter<AdaptorMusic.ManViweHolder>() {

    lateinit var binding: ItemHomeMusicBinding

    inner class ManViweHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun bindViews(dataMusic: DataMusic) {
            binding.txtMusic.text = dataMusic.subjectMusic
            binding.txtSubMusic.text = dataMusic.subtitletMusic
            binding.txtUrlMusic.text = dataMusic.urlMusic


            Glide.with(itemView.context).load(dataMusic.imgHomeMusic).transform(
                RoundedCornersTransformation(32, 8)
            ).into(binding.imgHomeMusic)
            itemView.setOnClickListener{
                itemEventsMusic.onItemClickedMusic(dataMusic)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManViweHolder {
        binding = ItemHomeMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ManViweHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return dataMusic.size
    }

    override fun onBindViewHolder(holder: ManViweHolder, position: Int) {
        holder.bindViews(dataMusic[position])
    }


    interface ItemEventsMusic {

        fun onItemClickedMusic(dataMusic: DataMusic)

    }

}