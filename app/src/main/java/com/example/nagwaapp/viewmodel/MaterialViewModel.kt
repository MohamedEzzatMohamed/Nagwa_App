package com.example.nagwaapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nagwaapp.models.Material
import com.example.nagwaapp.network.RetrofitClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MaterialViewModel : ViewModel() {

    private val TAG : String = MaterialViewModel::class.java.simpleName
    private val _materialList = MutableLiveData<ArrayList<Material>>()
    val materialList: LiveData<ArrayList<Material>>
        get() = _materialList

    val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    init {
        _status.value = "loading"
        _materialList.value = ArrayList()
    }

    fun getMaterialList(){
        _status.value = "loading"
//        var innerList: ArrayList<Material>
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClass.apiInterface.getMaterial()
                }
                Log.d(TAG, "getMaterialList: ${response.body()!!.get(0).status.toString()}")
                _status.value = "done"
                _materialList.postValue(response.body())

            } catch (error: Exception) {
                Log.d(TAG, "getMaterialList: $error")
                _status.value = "failed"
            }
        }
    }

}