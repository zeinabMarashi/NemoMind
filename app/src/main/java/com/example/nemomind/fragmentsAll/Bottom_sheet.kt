package com.example.nemomind.fragmentsAll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.nemomind.R
import com.example.nemomind.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class bottom_sheet :  BottomSheetDialogFragment(){
    lateinit var binding: FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBottomSheetBinding.inflate(layoutInflater, null, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // for choose category in bottom shit
        val category= listOf(

            "وظایف فوری و مهم",
            "وظایف غیر فوری اما مهم",
            "وظایف فوری اما غیر مهم",
            "وظایف غیز فوری و غیر مهم",

            )
        val adaptor= ArrayAdapter(requireContext(), R.layout.item_text_category_checklist,category)
        (binding.txtRecordCategory.editText as AutoCompleteTextView).setAdapter(adaptor)



    }
}


