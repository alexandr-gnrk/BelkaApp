package com.example.belkaapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class EventProperty(
    val event_id: String,
    val name: String,
    val event_time: String,
    val details: String,
    @Json(name = "image") val imgSrcUrl: String) : Parcelable