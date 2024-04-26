package com.example.nemomind.fragmentsAll

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.nemomind.R
import com.example.nemomind.databinding.FragmentEducationPageBinding
import com.example.nemomind.datas.DataEducation
import com.google.android.material.slider.Slider
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.util.Locale
import java.util.Timer
import java.util.TimerTask

class EducationPage : Fragment() {

    lateinit var binding: FragmentEducationPageBinding
    lateinit var mediaPlayer: MediaPlayer
    lateinit var timer: Timer
    lateinit var listEducationMusic: String
    var isPlaying = true
    var isUserChanging = false
    var isMute = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEducationPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        getDataMusic()
        prepareMusic()

        binding.btnPlayPauseMusic.setOnClickListener { configureMusic() }
        binding.btnGoBeforeMusic.setOnClickListener { goBeforeMusic() }
        binding.btnGoAfterMusic.setOnClickListener { goAfterMusic() }
        binding.btnVolumeOnOffMusic.setOnClickListener { configureVolume() }

        binding.sliderMusic.addOnChangeListener { slider, value, fromUser ->
            binding.txtLeftMusic.text = convertMillisToString(value.toLong())
            isUserChanging = fromUser
        }

        binding.sliderMusic.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {

            override fun onStartTrackingTouch(slider: Slider) {


            }

            override fun onStopTrackingTouch(slider: Slider) {
                mediaPlayer.seekTo(slider.value.toInt())
            }
             })

    }
    private fun getDataMusic() {

        val dataEducation: DataEducation? = arguments?.getParcelable("dataEducation")
        binding.txtSbtitleEducationPage.text = dataEducation!!.subjectEducation
        binding.txtMainEducationPage.text = dataEducation.textEducation
        binding.txtSubjectEducationPage.text = dataEducation.subjectEducation
        listEducationMusic = dataEducation.urlEducation

        Glide.with(requireContext()).load(dataEducation.imgHomeEducation).transform(
            RoundedCornersTransformation(32, 8)
        ).into(binding.imgEducationPage)

//        val animRotate = RotateAnimation(
//            0f,
//            360f,
//            Animation.RELATIVE_TO_SELF,
//            0.5f,
//            Animation.RELATIVE_TO_SELF,
//            0.5f)
//
//        animRotate.duration = convertMillisToString(mediaPlayer.duration.toLong()).toLong()
//        binding.imageCover.startAnimation(animRotate)
    }

    @SuppressLint("InlinedApi")
    private fun configureVolume() {

        val audioManager = requireActivity().getSystemService(Context.AUDIO_SERVICE) as AudioManager

        if (isMute) {
            audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI)
            binding.btnVolumeOnOffMusic.setImageResource(R.drawable.ic_volume_on)
            isMute = false

        } else {
            audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI)
            binding.btnVolumeOnOffMusic.setImageResource(R.drawable.ic_volume_off)
            isMute = true
        }

    }

    private fun goAfterMusic() {

        val now = mediaPlayer.currentPosition
        val newValue = now + 15000
        mediaPlayer.seekTo(newValue)

    }

    private fun goBeforeMusic() {

        val now = mediaPlayer.currentPosition
        val newValue = now - 15000
        mediaPlayer.seekTo(newValue)

    }

    private fun configureMusic() {

        if (isPlaying) {

            mediaPlayer.pause()
            binding.btnPlayPauseMusic.setImageResource(R.drawable.ic_play)
            isPlaying = false

        } else {

            mediaPlayer.start()
            binding.btnPlayPauseMusic.setImageResource(R.drawable.ic_pause)
            isPlaying = true

        }

    }

    private fun prepareMusic() {

        mediaPlayer = MediaPlayer.create(requireContext(), Uri.parse(listEducationMusic))
        mediaPlayer.start()
        isPlaying = true

        binding.btnPlayPauseMusic.setImageResource(R.drawable.ic_pause)

        binding.sliderMusic.valueTo = mediaPlayer.duration.toFloat()

        binding.txtRightMusic.text = convertMillisToString(mediaPlayer.duration.toLong())

        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                binding.root.post {
                    if (!isUserChanging) {
                        binding.sliderMusic.value = mediaPlayer.currentPosition.toFloat()
                    }
                }
            }
        }, 1000, 1000)

    }

    private fun convertMillisToString(duration: Long): String {

        val second = duration / 1000 % 60
        val minute = duration / (1000 * 60) % 60

        return java.lang.String.format(Locale.US, "%02d:%02d", minute, second)
    }

    override fun onDestroy() {
        super.onDestroy()

        timer.cancel()
        mediaPlayer.release()

    }







//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home) {
//            onBackPressed()
//        }
//        return true
//    }
}