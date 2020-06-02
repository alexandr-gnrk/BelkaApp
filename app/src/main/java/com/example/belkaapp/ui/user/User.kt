package com.example.belkaapp.ui.user

class User(var name: String = "",
            var surname: String = "",
            var phone_number: String = "",
            var email: String = "",
            var id: Int = -1)
{
    fun clear() {
        name = ""
        surname = ""
        phone_number = ""
        email = ""
        id = -1
    }
}
