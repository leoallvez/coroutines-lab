package com.devtides.androidcoroutinesretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devtides.androidcoroutinesretrofit.model.Country
import com.devtides.androidcoroutinesretrofit.model.CountriesService
import kotlinx.coroutines.*

class ListViewModel: ViewModel() {

    private val countryService = CountriesService.getCountriesService()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}}")
    }
    var job: Job? = null

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = countryService.getCountries()
            //update UI
            withContext(Dispatchers.Main) {
                when(response.isSuccessful) {
                    true -> onSuccess(response.body())
                    else -> onError("Error: ${response.message()}")
                }
            }
        }
    }

    private fun onSuccess(countryList: List<Country>?) {
        countryList?.let {
            countries.value = countryList
        }
        countryLoadError.value = ""
        loading.value = false
    }

    private fun onError(message: String) {
        countryLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}