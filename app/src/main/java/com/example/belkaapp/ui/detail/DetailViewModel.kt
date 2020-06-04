package com.example.belkaapp.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.belkaapp.network.EventProperty
import com.example.belkaapp.R

/**
 *  The [ViewModel] associated with the [DetailFragment], containing information about the selected
 *  [MarsProperty].
 */
class DetailViewModel( eventProperty: EventProperty,
                       app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<EventProperty>()
    val selectedProperty: LiveData<EventProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = eventProperty
    }

    // The displayPropertyPrice formatted Transformation Map LiveData, which displays the sale
    // or rental price.
//    val displayPropertyPrice = Transformations.map(selectedProperty) {
//        app.applicationContext.getString(
//            when (it.isRental) {
//                true -> R.string.display_price_monthly_rental
//                false -> R.string.display_price
//            }, it.price)
//    }

    // The displayPropertyType formatted Transformation Map LiveData, which displays the
    // "For Rent/Sale" String
//    val displayPropertyType = Transformations.map(selectedProperty) {
//        app.applicationContext.getString(R.string.display_type,
//            app.applicationContext.getString(
//                when(it.isRental) {
//                    true -> R.string.type_rent
//                    false -> R.string.type_sale
//                }))
//    }
}

