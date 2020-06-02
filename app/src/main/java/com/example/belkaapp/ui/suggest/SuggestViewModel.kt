package com.example.belkaapp.ui.suggest

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belkaapp.ui.storage.Storage

class SuggestViewModel : ViewModel() {
    val eventName = MutableLiveData<String>()
    val eventDate = MutableLiveData<String>()
    val eventDescription = MutableLiveData<String>()
    val context = MutableLiveData<Context>()

    init {
        reset()
    }

    fun reset() {
        eventName.value = ""
        eventDate.value = ""
        eventDescription.value = ""
    }

    fun makeSuggestion(event_name: String, event_date: String, event_description: String): String {
        Log.i("SuggestViewModel", "makeSuggestion {$event_name}, {$event_date}, {$event_description}")
        Log.i("SuggestViewModel", "check is user logged in")
        if (! Storage(context.value!!).isUserExist()) {
            return "Спочатку вкажіть інформацію про себе в розділі 'Акаунт'"
        }
        Log.i("SuggestViewModel", "send suggestion to the server")
        Log.i("SuggestViewModel", "clean inputs")
        reset()
        return "Ваша пропозиція успішно надіслана!"
    }
}