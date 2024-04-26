package com.example.nemomind.datas

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataMusic(
    val imgHomeMusic: String,
    val subjectMusic: String,
    val subtitletMusic: String,
    val urlMusic: String

):Parcelable
