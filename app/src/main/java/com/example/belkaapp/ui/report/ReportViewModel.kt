package com.example.belkaapp.ui.report

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReportViewModel: ViewModel()  {
    init {
        Log.i("ReporViewModel", "ReportViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ReporViewModel", "ReportViewModel deleted!")
    }
}