package com.example.cakearea.repository

import com.example.cakearea.model.CakeResponseItem
import com.example.cakearea.remote.RetrofitClient

class CakeRepository {
    suspend fun getCakes() : Result<List<CakeResponseItem>> = try {
        val flowers = RetrofitClient.api.getCakes()
        Result.success(flowers)
    }
    catch (e: Exception) {
        Result.failure(e)
    }
}