package com.example.coroutinewithlivedata_test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val counter = MutableLiveData<Int>()

    init {
        counter.value = 0
    }
}