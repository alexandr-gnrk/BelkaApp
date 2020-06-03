package com.example.belkaapp.ui.events

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.belkaapp.network.BelkaApi
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.launch
//import retrofit2.Call
//import retrofit2.Response
//import retrofit2.Callback
//import javax.security.auth.callback.Callback

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.belkaapp.network.BelkaApi
import com.example.belkaapp.network.EventProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
//import com.example.meetplanner.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class BelkaApiStatus { LOADING, ERROR, DONE }

class EventsViewModel : ViewModel() {

    private val _status = MutableLiveData<BelkaApiStatus>()

    val status: LiveData<BelkaApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<EventProperty>>()

    val properties: LiveData<List<EventProperty>>
        get() = _properties
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        getEventsProperties()
    }


    private fun getEventsProperties() {
//        BelkaApi.retrofitService.getProperties().enqueue(object: Callback<List<EventProperty>> {
//            override fun onFailure(call: Call<List<EventProperty>>, t: Throwable) {
//                _response.value = "Failure: " + t.message
//            }
//
//            override fun onResponse(call: Call<List<EventProperty>>, response: Response<List<EventProperty>>) {
//                _response.value = "Success: ${response.body()?.size} Events retrived"
//            }
//        }
//        )
//        _response.value = "Set Events API to Response here!"
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
}