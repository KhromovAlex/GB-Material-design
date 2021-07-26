package com.example.gbmaterialdesign.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gbmaterialdesign.common.AppData
import com.example.gbmaterialdesign.data.model.EarthServerResponseData
import com.example.gbmaterialdesign.domain.repository.EarthRepository
import com.example.gbmaterialdesign.domain.repository.EarthRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EarthViewModel : ViewModel() {
    private val earthRepository: EarthRepository = EarthRepositoryImpl()
    private val liveDataForViewToObserve: MutableLiveData<AppData<EarthServerResponseData>> =
        MutableLiveData()

    fun loadData() {
        liveDataForViewToObserve.postValue(AppData.Loading())

        earthRepository.getDataEarth().enqueue(object : Callback<List<EarthServerResponseData>> {
            override fun onResponse(
                call: Call<List<EarthServerResponseData>>,
                response: Response<List<EarthServerResponseData>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    liveDataForViewToObserve.postValue(AppData.Success(response.body()!!.first()))
                } else {
                    val message = response.message()
                    if (message.isNullOrEmpty()) {
                        liveDataForViewToObserve.postValue(AppData.Error(Exception("Unidentified error")))
                    } else {
                        liveDataForViewToObserve.postValue(AppData.Error(Exception(message)))
                    }
                }
            }

            override fun onFailure(call: Call<List<EarthServerResponseData>>, t: Throwable) {
                liveDataForViewToObserve.postValue(AppData.Error(Exception(t)))
            }

        })
    }

    fun getData(): MutableLiveData<AppData<EarthServerResponseData>> = liveDataForViewToObserve
}
