package com.example.nagwaapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Material(
    val id: Int,
    val name: String,
    val type: String,
    val url: String,
    var status: String
) : Parcelable