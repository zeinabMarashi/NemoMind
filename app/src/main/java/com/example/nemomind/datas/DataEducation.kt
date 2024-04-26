package com.example.nemomind.datas

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataEducation(
    val imgHomeEducation: String,
    val subjectEducation: String,
    val subtitletEducation: String,
    val textEducation: String,
    val urlEducation:String
): Parcelable
