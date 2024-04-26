package com.example.nemomind.fragmentsAll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nemomind.R
import com.example.nemomind.adaptors.AdaptorMusic
import com.example.nemomind.adaptors.AdaptorTopMan
import com.example.nemomind.databinding.FragmentMusicShowMoreBinding
import com.example.nemomind.databinding.FragmentTopManShowMoreBinding
import com.example.nemomind.datas.DataMusic
import com.example.nemomind.datas.DataTopMan

class music_show_more : Fragment(), AdaptorMusic.ItemEventsMusic {



    lateinit var binding: FragmentMusicShowMoreBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMusicShowMoreBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        runMusic()
    }

    private fun runMusic() {
        val dataMusic = arrayListOf<DataMusic>(

            DataMusic(
                "https://s6.uupload.ir/files/پرنده_در_چنگل_k7et.jpeg",
                "موسیقی بیکلام به",
                "همراه صدای پرندگان",
                "https://dls.music-fa.com/tagdl/1402/Masoud%20Kiani%20-%20Yadete%20(320).mp3"
            ),
            DataMusic(
                "https://s6.uupload.ir/files/طلوع_خورشید_بین_درختان_79v3.jpg",
                "آهنگ طلوع خورشید ",
                "در بین درختان",
                "https://dls.music-fa.com/tagdl/1402/Babak%20Hosseini%20-%20Cheshm%20Rooshan%20(320).mp3"
            ),
            DataMusic(
                "https://s6.uupload.ir/files/تنها_با_طبیعت_incy.jpeg",
                "موسیقی بی کلام",
                "تنها با طبیعت",
                "https://dls.music-fa.com/tagdl/1402/Macan%20Band%20-%20Ghashang%20Ghashang%20(320).mp3"
            ),
            DataMusic(
                "https://s6.uupload.ir/files/آبشار_oajt.jpg",
                "موسیقی بیکلام به",
                "همراه صدای آبشار",
                "https://dls.music-fa.com/tagdl/1402/Tm%20Bax%20-%20Ragbari%20(320).mp3"
            ),
            DataMusic(
                "https://s6.uupload.ir/files/دریا_essq.jpg",
                "موسیقی بیکلام به",
                "همراه صدای دریا",
                "https://dls.music-fa.com/tagdl/1402/Fazel%20Deriss%20-%20Kam%20Nazashtam%20(320).mp3"
            ),
            DataMusic(
                "https://s6.uupload.ir/files/کلبه_rm1u.jpg",
                "موسیقی بیکلام",
                "در جنگل",
                "https://dls.music-fa.com/tagdl/1402/Ali%20Edein%20-%20Baghe%20Vahsh%20(320).mp3"
            )


        )
        val adapterMusic = AdaptorMusic(dataMusic, this)
        binding.recyclerShowMoreMusic.adapter = adapterMusic
        binding.recyclerShowMoreMusic.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }
    override fun onItemClickedMusic(dataMusic: DataMusic) {

        val bundel = Bundle()
        bundel.putParcelable("dataMusic", dataMusic)
        findNavController().navigate(R.id.action_music_show_more_to_music_page, bundel)

    }



}