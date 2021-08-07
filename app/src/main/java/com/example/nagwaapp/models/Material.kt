package com.example.nagwaapp.models

data class Material(
    val id: Int,
    val name: String,
    val type: String,
    val url: String,
    val status: String = "download"
)