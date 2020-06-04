package com.example.belkaapp.ui.suggest

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belkaapp.network.BelkaApi
import com.example.belkaapp.network.InformProperty
import com.example.belkaapp.network.ResponseProperty
import com.example.belkaapp.network.SuggestProperty
import com.example.belkaapp.ui.storage.Storage
import com.example.belkaapp.ui.user.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SuggestViewModel : ViewModel() {
    val eventName = MutableLiveData<String>()
    val eventDate = MutableLiveData<String>()
    val eventDescription = MutableLiveData<String>()
    val context = MutableLiveData<Context>()
    val msg = MutableLiveData<String>()

    init {
        reset()
    }

    fun reset() {
        eventName.value = ""
        eventDate.value = ""
        eventDescription.value = ""
    }

    fun makeSuggestion(event_name: String, event_date: String, event_description: String) {
        Log.i("SuggestViewModel", "makeSuggestion {$event_name}, {$event_date}, {$event_description}")
        Log.i("SuggestViewModel", "check is user logged in")
        if (! Storage(context.value!!).isUserExist()) {
            msg.value = "Спочатку вкажіть інформацію про себе в розділі 'Акаунт'"
            return
        }
        val user: User = Storage(context.value!!).loadUser()
        var suggestProperty = SuggestProperty(user.id, event_name, event_date, event_description)
        BelkaApi.retrofitService.sendSuggest(suggestProperty).enqueue(object:
            Callback<ResponseProperty> {
            override fun onFailure(call: Call<ResponseProperty>?, t: Throwable?) {
                msg.value = "Failed to connect to server"
                Log.v("Failure", t.toString())
            }
            override fun onResponse(call: Call<ResponseProperty>?, response: Response<ResponseProperty>?) {
                when (response?.code()) {
                    200 -> {
                        msg.value = "Ваша пропозиція успішно надіслана!"
                    }
                    401 -> msg.value = response.errorBody()?.string().toString()
                    500 -> msg.value = "Internal server error"
                }
            }
        })
        Log.i("SuggestViewModel", "send suggestion to the server")
        Log.i("SuggestViewModel", "clean inputs")
        reset()
    }
}