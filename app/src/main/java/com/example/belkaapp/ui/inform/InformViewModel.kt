package com.example.belkaapp.ui.inform

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belkaapp.network.BelkaApi
import com.example.belkaapp.network.InformProperty
import com.example.belkaapp.network.ResponseProperty
import com.example.belkaapp.network.UserIdProperty
import com.example.belkaapp.ui.storage.Storage
import com.example.belkaapp.ui.user.User
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InformViewModel : ViewModel() {
    val reason = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val context = MutableLiveData<Context>()
    val msg = MutableLiveData<String>()

    init {
        reset()
    }

    fun reset() {
        reason.value = ""
        description.value = ""
    }

    fun informAbout(reas: String, descrip: String) {
        Log.i("InformViewModel", "Informing about {$reas}, {$descrip}")
        if (! Storage(context.value!!).isUserExist()) {
            msg.value = "Спочатку вкажіть інформацію про себе в розділі 'Акаунт'"
            return
        }

        Log.i("InformViewModel", "Check for gps coordinates")
        val user: User = Storage(context.value!!).loadUser()
        Log.i("InformViewModel", user.id)
        var informProperty = InformProperty(Storage(context.value!!).loadUser().id, reas, descrip)
        BelkaApi.retrofitService.sendInform(informProperty).enqueue(object: Callback<ResponseProperty> {
            override fun onFailure(call: Call<ResponseProperty>?, t: Throwable?) {
                msg.value = "Failed to connect to server"
                Log.v("Failure", t.toString())
            }
            override fun onResponse(call: Call<ResponseProperty>?, response: Response<ResponseProperty>?) {
                when (response?.code()) {
                    200 -> {
                        msg.value = "Ваше повідомлення успішно надіслано!"
                    }
                    401 -> msg.value = response.errorBody()?.string().toString()
                    500 -> msg.value = "Internal server error"
                }
            }
        })
        Log.i("InformViewModel", "Reset information")
        reset()
    }
}