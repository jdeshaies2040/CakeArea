package com.example.cakearea.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cakearea.model.CakeResponseItem
import com.example.cakearea.repository.CakeRepository
import kotlinx.coroutines.launch

class CakeViewModel : ViewModel() {
    // 1 Access Repository Object - and extend ViewModel() class
    // 2 Use LiveData
    // 3 Coroutines

    // TODO Hilt DI
    private val repository = CakeRepository()

    // 3 States - Loading, Success, Error
    // success livedata
    private val _cakes = MutableLiveData<List<CakeResponseItem>>() // can change
    val flowers : LiveData<List<CakeResponseItem>> = _cakes // cannot change - read-only

    // loading
    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    // error
    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    fun fetchFlowers() {
        viewModelScope.launch {
            _loading.value = true
            repository.getCakes()
                .onSuccess { flowers ->
                    _cakes.value = flowers
                    _loading.value = false
                }
                .onFailure { exception ->
                    _error.value = exception.message
                    _loading.value = false
                }
        }
    }
}