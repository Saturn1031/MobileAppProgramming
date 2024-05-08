package com.example.ch18_joyce

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// https://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo?year=2024&pageNo=1&numOfRows=2&returnType=json&serviceKey=n6EBRN24jG%2BUrUXH%2FsU8SlHMyu1RBlJZvoO5woqXnoa0poCpn%2BiLZX0D3RXUafvYhhFLUa%2FB%2Fr7n3ZJSWDGuuQ%3D%3D

class RetrofitConnection {
    companion object {
        private const val BASE_URL = "https://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/"

        val jsonNetworkService: NetworkService
        val jsonRetrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        init {
            jsonNetworkService = jsonRetrofit.create(NetworkService::class.java)
        }
    }
}