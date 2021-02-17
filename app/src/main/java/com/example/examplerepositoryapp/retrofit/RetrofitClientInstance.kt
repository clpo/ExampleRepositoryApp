package com.example.examplerepositoryapp.retrofit

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.ZonedDateTime


object RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://api.github.com/"

    var gsonCreated = GsonBuilder().registerTypeAdapter(
        LocalDateTime::class.java,
        JsonDeserializer<LocalDateTime?> { json, type, jsonDeserializationContext ->
            ZonedDateTime.parse(
                json.asJsonPrimitive.asString
            ).toLocalDateTime()
        }).create()

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                val gson = gsonCreated
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            }
            return retrofit
        }
}