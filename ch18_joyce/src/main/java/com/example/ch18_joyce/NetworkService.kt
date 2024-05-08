package com.example.ch18_joyce

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// https://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo?year=2024&pageNo=1&numOfRows=2&returnType=json&serviceKey=n6EBRN24jG%2BUrUXH%2FsU8SlHMyu1RBlJZvoO5woqXnoa0poCpn%2BiLZX0D3RXUafvYhhFLUa%2FB%2Fr7n3ZJSWDGuuQ%3D%3D

interface NetworkService {
    @GET("getUlfptcaAlarmInfo")
    fun getJsonList(
        @Query("year") year: Int,
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("returnType") returnType: String,
        @Query("serviceKey") serviceKey: String
    ): Call<JsonResponse>
}