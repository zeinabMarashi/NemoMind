package com.example.nemomind.fragmentsAll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.nemomind.databinding.FragmentBookPageBinding
import com.example.nemomind.databinding.FragmentTopManPageBinding
import com.example.nemomind.datas.DataBook
import com.example.nemomind.datas.DataTopMan
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


class top_man_page : Fragment() {
    lateinit var binding:FragmentTopManPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopManPageBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val dataTopMan: DataTopMan? = arguments?.getParcelable("dataTopMan")


        binding.txtSubjectTopManPage.text = dataTopMan!!.subjectTopMan.toString()
        binding.txtSbtitleTopManPage.text = dataTopMan.subtitletTopMan.toString()
        binding.txtMainTopManPage.text = dataTopMan.detailTopMan.toString()


        Glide.with(requireContext()).load(dataTopMan.imgHomeTopMan.toString()).transform(
            RoundedCornersTransformation(32, 8)
        ).into(binding.imgTopManPage)

    }
}