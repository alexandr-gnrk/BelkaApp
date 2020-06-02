package com.example.belkaapp.network
//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.POST

private const val BASE_URL = "http://192.168.1.2:5000/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
//    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface BelkaApiService {

    @GET("events")
    fun getProperties():
            Call<List<EventProperty>>
//            Deferred<List<MarsProperty>>
}


object BelkaApi {
    val retrofitService : BelkaApiService by lazy {
        retrofit.create(BelkaApiService::class.java)
    }
}