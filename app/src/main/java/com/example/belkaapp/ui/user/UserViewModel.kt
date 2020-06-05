package com.example.belkaapp.ui.user

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belkaapp.network.BelkaApi
import com.example.belkaapp.network.UserIdProperty
import com.example.belkaapp.network.UserProperty
import com.example.belkaapp.ui.storage.Storage
import retrofit2.Call
import retrofit2.Response

class UserViewModel : ViewModel() {
    val user = MutableLiveData<User>()
    val context = MutableLiveData<Context>()
    val msg = MutableLiveData<String>()

    init {
        Log.i("UserViewModel", "Inint UserViewModel, need to update user variable")
    }

    fun loadUserFromStorage() {
        user.value = Storage(context.value!!).loadUser()
    }

    fun updateUser(newUser: User) {
        Log.i("UserViewModel", "Updating user {$newUser}")
        Log.i("UserViewModel", "Send user info to the server")
        Log.i("UserViewModel", "Get response from the server")
        Log.i("UserViewModel", "Save user in the file")

        val userProperty = UserProperty(newUser.name, newUser.surname, newUser.phone_number, newUser.email, user.value!!.id)
        Log.i("User", (userProperty.id+ userProperty.name+ userProperty.surname+ userProperty.phone_number).toString())
        BelkaApi.retrofitService.updateUser(userProperty).enqueue(object: retrofit2.Callback<UserIdProperty> {
            override fun onFailure(call: Call<UserIdProperty>?, t: Throwable?) {
                msg.value = "Failed to connect to server"
                Log.v("Failure", t.toString())
            }
            override fun onResponse(call: Call<UserIdProperty>?, response: Response<UserIdProperty>?) {
                when (response?.code()) {
                    200 -> {
                        successfulUpdate(newUser, requireNotNull(response.body()))
                        msg.value = "Інформація успішно оновлена"
                    }
                    401 -> msg.value = response.errorBody()?.string().toString()
                    500 -> msg.value = "Internal server error"
                }
            }
        })
    }

    fun successfulUpdate(newUser: User, userIdProperty: UserIdProperty) {
        Log.i("successfulUpdate", userIdProperty.user_id)
        newUser.id = userIdProperty.user_id;
        Storage(context.value!!).saveUser(newUser)
        loadUserFromStorage()
    }
}