package com.ozanyazici.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ozanyazici.retrofitkotlin.adapter.RecyclerViewAdapter
import com.ozanyazici.retrofitkotlin.databinding.ActivityMainBinding
import com.ozanyazici.retrofitkotlin.model.CryptoModel
import com.ozanyazici.retrofitkotlin.service.CryptoAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    //Disposable
    private lateinit var compositeDisposable: CompositeDisposable
    //private var job : Job? = null
    //Coroutine hata kontrolü için
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        compositeDisposable = CompositeDisposable()

        loadData()

    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()//retrofit nesnesi oluşturuyorum ve konfigürasyonlarını girip buna göre inşa ediyorum.
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        //Aynı işlemin coroutines kullanılarak yapılışı
        /*job =*/ lifecycleScope.launch(Dispatchers.IO + exceptionHandler) {
            supervisorScope {
                val response = retrofit.getData()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            cryptoModels = ArrayList(it)
                            cryptoModels?.let {
                                recyclerViewAdapter = RecyclerViewAdapter(it)
                                binding.recyclerView.adapter = recyclerViewAdapter
                            }
                        }
                    }
                }
            }
        }
        //Genel olarak, CoroutineScope daha geniş bir kapsam sunar ve sınıflar içinde kullanılabilirken,
        //coroutineScope daha çok suspend işlemleri içinde paralel işlemleri yönetmek için kullanılır.
        //Bu iki kavramın kullanımı, belirli bir senaryoya ve ihtiyaca bağlı olarak değişebilir.

       /*
       compositeDisposable.add(retrofit.getData()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(this::handleResponse))
        */

        /* RxJava kullanmadan böyle yapılıyor call ile. karmaşık projelerde retrofit RxJava ile birlikte kullanılıyor.

        val service = retrofit.create(CryptoAPI::class.java) //service oluşturuyoruz CyrptoAPI yi service olarak kullanıcam diyorum

        val call = service.getData()

        call.enqueue(object: Callback<List<CryptoModel>> { //Asenkron bir şekilde çağrı yapıyorum ve başarılı olup olmama durumuna göre ele alıyorum.
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {

              cryptoModels?.let {
                recyclerViewAdapter = RecyclerViewAdapter(it)
                binding.recyclerView.adapter = recyclerViewAdapter
        }
                        /*
                        for (cryptoModel: CryptoModel in cryptoModels!!) {
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        }
                         */
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        }) */
    }

    /*
    private fun handleResponse(cryptoList: List<CryptoModel>) {
        cryptoModels = ArrayList(cryptoList)

        cryptoModels?.let {
            recyclerViewAdapter = RecyclerViewAdapter(it)
            binding.recyclerView.adapter = recyclerViewAdapter
        }

    }
     */

    override fun onDestroy() {
        super.onDestroy()
        //job?.cancel() lifecyclescope kullandığım için job kullanmama gerek kalmadı
    }
}