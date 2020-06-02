package com.example.belkaapp.ui.inform

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belkaapp.ui.storage.Storage

class InformViewModel : ViewModel() {
    val reason = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val context = MutableLiveData<Context>()

    init {
        reset()
    }

    fun reset() {
        reason.value = ""
        description.value = ""
    }

    fun informAbout(reas: String, descrip: String): String {
        Log.i("InformViewModel", "Informing about {$reas}, {$descrip}")
        if (! Storage(context.value!!).isUserExist()) {
            return "Спочатку вкажіть інформацію про себе в розділі 'Акаунт'"
        }
        Log.i("InformViewModel", "Check for gps coordinates")
        Log.i("InformViewModel", "Save information on the disk")
        Log.i("InformViewModel", "Reset information")
        reset()
        return "Ваше повідомлення успішно надіслано!"
//        return "Спочатку вкажіть інформацію про себе в розділі 'Акаунт'"
    }
}