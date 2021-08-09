package com.example.nagwaapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.nagwaapp.R

@BindingAdapter("statusImage")
fun ImageView.setDownloadingImage(status: String) {
    setImageResource(
        when (status) {
            "downloading" -> R.drawable.ic_watch_later
            "downloaded" -> R.drawable.ic_downloaded
            else -> R.drawable.ic_download
        }
    )
}
@BindingAdapter("typeImage")
fun ImageView.setTypeImage(status: String) {
    setImageResource(
        when (status) {
            "PDF" -> R.drawable.ic_pdf
            "VIDEO" -> R.drawable.ic_youtube
            else -> R.drawable.ic_watch_later
        }
    )
}