package com.example.nemomind.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nemomind.databinding.ItemHomeBookBinding
import com.example.nemomind.datas.DataBook
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class AdaptorBook(val dataBook: ArrayList<DataBook>, val itemEventsBook:ItemEventsBook)  : RecyclerView.Adapter<AdaptorBook.ManViweHolder>() {

    lateinit var binding: ItemHomeBookBinding

    inner class ManViweHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun bindViews(dataBook: DataBook) {
            binding.txtSubjectBook.text = dataBook.subjectBook
            binding.txtSubBook.text = dataBook.subtitletBook
            binding.txtBookMain.text = dataBook.detailBook


            Glide.with(itemView.context).load(dataBook.imgHomeBook).transform(
                RoundedCornersTransformation(32, 8)
            ).into(binding.imgHomeBook)
            itemView.setOnClickListener{
                itemEventsBook.onItemClickedBook(dataBook)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManViweHolder {
        binding = ItemHomeBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ManViweHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return dataBook.size
    }

    override fun onBindViewHolder(holder: ManViweHolder, position: Int) {
        holder.bindViews(dataBook[position])
    }


    interface ItemEventsBook {

        fun onItemClickedBook(dataBook: DataBook)

    }

}