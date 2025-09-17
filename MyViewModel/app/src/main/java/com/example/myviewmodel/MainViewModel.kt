package com.example.myviewmodel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var result = 0

    fun calculate(width: String, height: String, lenght: String){
        result = width.toInt() * height.toInt() * lenght.toInt()
    }
}