package com.example.cakearea.remote

import com.example.cakearea.model.CakeResponseItem
import retrofit2.http.GET

// Base: https://gist.githubusercontent.com/
// Relative: hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json

interface CakeApi {
    @GET("hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json")
    suspend fun getCakes() : List<CakeResponseItem>
}