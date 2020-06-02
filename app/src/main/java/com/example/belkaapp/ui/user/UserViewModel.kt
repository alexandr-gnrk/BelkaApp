package com.example.belkaapp.ui.user

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belkaapp.ui.storage.Storage

class UserViewModel : ViewModel() {
    val user = MutableLiveData<User>()
    val context = MutableLiveData<Context>()

    init {
        Log.i("UserViewModel", "Inint UserViewModel, need to update user variable")
    }

    fun loadUserFromStorage() {
        user.value = Storage(context.value!!).loadUser()
    }

    fun updateUser(newUser: User): String {
        Log.i("UserViewModel", "Updating user {$newUser}")
        Log.i("UserViewModel", "Send user info to the server")
        Log.i("UserViewModel", "Get response from the server")
        Log.i("UserViewModel", "Save user in the file")
        newUser.id++;
        Storage(context.value!!).saveUser(newUser)
        return "Інформація успішно оновлена"
//        return "Спочатку вкажіть інформацію про себе в розділі 'Акаунт'"
    }
    // TODO: Implement the ViewModel
}