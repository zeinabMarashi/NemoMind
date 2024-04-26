package com.example.nemomind.fragmentsAll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.nemomind.databinding.FragmentBookPageBinding
import com.example.nemomind.datas.DataBook
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

lateinit var binding:FragmentBookPageBinding

class book_page : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val dataBook: DataBook? = arguments?.getParcelable("dataBook")


        binding.txtSubjectBookPage.text = dataBook!!.subjectBook.toString()
        binding.txtSbtitleBookPage.text = dataBook.subtitletBook.toString()
        binding.txtMainBookPage.text = dataBook.detailBook.toString()


        Glide.with(requireContext()).load(dataBook.imgHomeBook.toString()).transform(
            RoundedCornersTransformation(32, 8)
        ).into(binding.imgBookPage)

    }
}