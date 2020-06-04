package com.example.belkaapp.ui.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.belkaapp.ui.user.User

class Storage(context: Context) {
    private var prefName: String = "UserPref"
    private var context_mode: Int = Context.MODE_PRIVATE
    private lateinit var pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(prefName, context_mode)
    }

    fun saveUser(user: User) {
        val editor = pref.edit()
        editor.apply {
            putString("name", user.name)
            putString("surname", user.surname)
            putString("phone_number", user.phone_number)
            putString("email", user.email)
            putString("user_id", user.id)
        }
        editor.apply()
    }

    fun loadUser(): User {
        return User(
            pref.getString("name", "")!!,
            pref.getString("surname", "")!!,
            pref.getString("phone_number", "")!!,
            pref.getString("email", "")!!,
            pref.getString("user_id", "")!!
        )
    }

    fun isUserExist(): Boolean {
        return loadUser().id != ""
    }
}
