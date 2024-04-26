package com.example.nemomind.datas

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTopMan(
    val imgHomeTopMan: String,
    val subjectTopMan: String,
    val subtitletTopMan: String,
    val detailTopMan:String

):Parcelable
