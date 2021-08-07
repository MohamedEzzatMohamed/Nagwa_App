package com.example.nagwaapp.viewmodel

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

    private val materialsListLiveData = MutableLiveData<ArrayList<Material>>()
    public val materialList: LiveData<ArrayList<Material>>
        get() = materialsListLiveData

    //check if the requests is success
    private var _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    init {
        _status.value = "loading"
    }

    fun getMaterialList(){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    materialsListLiveData.value = RetrofitClass.apiInterface.getMaterial()
                }

            } catch (error: Exception) {

            }
        }
    }
}