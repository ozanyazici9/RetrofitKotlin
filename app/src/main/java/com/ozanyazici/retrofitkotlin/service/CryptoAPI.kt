package com.ozanyazici.retrofitkotlin.service

import com.ozanyazici.retrofitkotlin.model.CryptoModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    //GET, POST, UPDATE, DELETE

    //https://raw.githubusercontent.com/
    //atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json") //hangi adresten veri çekileceği
    fun getData(): Observable<List<CryptoModel>>

    //fun getData(): Call<List<CryptoModel>> //veri çekme işlemini bir metod olarak tanımlıyoruz ve ne döndüreceğini yazıyoruz.
}