package com.example.belkaapp.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.belkaapp.network.EventProperty


class DetailViewModel( eventProperty: EventProperty,
                       app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<EventProperty>()
    val selectedProperty: LiveData<EventProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = eventProperty
    }


}

