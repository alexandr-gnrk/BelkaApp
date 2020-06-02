package com.example.belkaapp.network

import com.squareup.moshi.Json

class EventProperty(
    val event_id: String,
    val name: String,
    val event_time: String,
    val details: String,
    @Json(name = "image") val imgSrcUrl: String
)