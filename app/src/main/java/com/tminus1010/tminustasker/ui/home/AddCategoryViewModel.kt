package com.tminus1010.tminustasker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddCategoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Add a category"
    }
    val text: LiveData<String> = _text
}