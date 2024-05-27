package com.example.ch18_image

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo?year=2024&pageNo=1&numOfRows=10&returnType=xml&serviceKey=서비스키(일반 인증키(Encoding))

interface NetworkService {
    // http://localhost/PHP_connection.php
    @GET("PHP_connection.php")
    fun getPhpList(): Call<PhpResponse>

    @GET("getCertImgListServiceV3")
    fun getXmlList(
        @Query("prdlstNm") name:String,
        @Query("pageNo") pageNo:Int,
        @Query("numOfRows") numOfRows:Int,
        @Query("returnType") returnType:String,
        @Query("ServiceKey") apiKey:String
    ): Call<XmlResponse>
}