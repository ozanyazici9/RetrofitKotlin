package com.ozanyazici.retrofitkotlin.service

import com.ozanyazici.retrofitkotlin.model.CryptoModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {

    //GET, POST, UPDATE, DELETE

    //https://raw.githubusercontent.com/
    //atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json") //hangi adresten veri çekileceği
    //Coroutines kullanıldığında
    suspend fun getData(): Response<List<CryptoModel>>
    //suspend ile işaretlenmiş bir metot, UI thread'in bloke olmasını engeller
    //ve bu süreçte kullanıcının uygulama üzerindeki etkileşimlerine devam etmesine izin verir.

    //Retrofit + RxJava kullanıldığında
    //fun getData(): Observable<List<CryptoModel>>

    //Retrofit tek başına kullanıldığında: veri çekme işlemini bir metod olarak tanımlıyoruz ve ne döndüreceğini yazıyoruz.
    //fun getData(): Call<List<CryptoModel>>
}