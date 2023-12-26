package com.ozanyazici.retrofitkotlin.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(
    //@SerializedName("currency") gelecek veriyle aynı ismi verirsem değişkene serializedname notasyonunu eklememe gerek yok.
    val currency: String,
    //@SerializedName("price")
    val price: String)
