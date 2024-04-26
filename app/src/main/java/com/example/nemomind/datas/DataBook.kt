package com.example.nemomind.datas

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataBook(
    val imgHomeBook: String,
    val subjectBook: String,
    val subtitletBook: String,
    val detailBook:String

):Parcelable
