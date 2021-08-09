package com.example.nagwaapp.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nagwaapp.models.Material
import com.example.nagwaapp.utils.Constant.ONE_SECOND
import com.example.nagwaapp.utils.Constant.TWENTY_SECOND

class DetailsViewModel : ViewModel() {

    private val _materialLiveData = MutableLiveData<Material>()
    val materialLiveData: LiveData<Material>
        get() = _materialLiveData

    private lateinit var timer: CountDownTimer

    // Livedata for the timer is finished to restart it
    private val _timerStart = MutableLiveData<Boolean>()
    val timerStart: LiveData<Boolean>
        get() = _timerStart

    // Livedata for the timer is finished to restart it
    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int>
        get() = _counter

    // Livedata for the timer is finished to restart it
    private val _materialStatus = MutableLiveData<String>()
    val materialStatus: LiveData<String>
        get() = _materialStatus


    init {
        _timerStart.value = false
        _materialStatus.value = "ready"
    }

    fun setMaterialDetails(material: Material) {
        _materialLiveData.value = material
        enhanceURL()
    }

    private fun enhanceURL(){
        if(materialLiveData.value!!.url[0]!='h')
            _materialLiveData.value!!.url.drop(1)
    }

    fun downloadMaterial() {
        if (materialStatus.value == "ready") {
            _materialStatus.value = "downloading"
            _materialLiveData.value!!.status = "downloading"
            startTimer(TWENTY_SECOND)
        }
    }

    // a timer function for fake download
    private fun startTimer(minutes: Long) {
        _timerStart.value = true
        timer = object : CountDownTimer(minutes, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                val counterLong =
                    (((TWENTY_SECOND - millisUntilFinished).toFloat() / TWENTY_SECOND.toFloat()) * 100.0)

                _counter.value = counterLong.toInt()
            }

            override fun onFinish() {
                _materialLiveData.value!!.status = "downloaded"
                _materialStatus.value = "downloaded"
                _timerStart.value = false
            }
        }
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        if(timerStart.value!!)
            timer.cancel()
    }
}