package com.example.belkaapp.ui.events


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belkaapp.network.BelkaApi
import com.example.belkaapp.network.EventProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class BelkaApiStatus { LOADING, ERROR, DONE }

class EventsViewModel : ViewModel() {

    private val _status = MutableLiveData<BelkaApiStatus>()

    val status: LiveData<BelkaApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<EventProperty>>()

    val properties: LiveData<List<EventProperty>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<EventProperty>()
    val navigateToSelectedProperty: LiveData<EventProperty>
        get() = _navigateToSelectedProperty

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getEventsProperties()
    }


    private fun getEventsProperties() {
        coroutineScope.launch {
            var getPropertiesDeferred = BelkaApi.retrofitService.getProperties()
            try {
                _status.value = BelkaApiStatus.LOADING
                var listResult = getPropertiesDeferred.await()
                _status.value = BelkaApiStatus.DONE
                if (listResult.size > 0) {
                    _properties.value = listResult
                }
            } catch (e: Exception) {
                _status.value = BelkaApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(eventProperty: EventProperty) {
        _navigateToSelectedProperty.value = eventProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}