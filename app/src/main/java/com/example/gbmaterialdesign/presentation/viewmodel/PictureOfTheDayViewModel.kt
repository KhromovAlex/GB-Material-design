package com.example.gbmaterialdesign.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gbmaterialdesign.common.AppData
import com.example.gbmaterialdesign.data.model.PODServerResponseData
import com.example.gbmaterialdesign.domain.repository.PODRepository
import com.example.gbmaterialdesign.domain.repository.PODRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel : ViewModel() {
    private val podRepository: PODRepository = PODRepositoryImpl()
    private val liveDataForViewToObserve: MutableLiveData<AppData<PODServerResponseData>> =
        MutableLiveData()

    fun loadData() {
        liveDataForViewToObserve.postValue(AppData.Loading())

        podRepository.getPOD().enqueue(object : Callback<PODServerResponseData> {
            override fun onResponse(
                call: Call<PODServerResponseData>,
                response: Response<PODServerResponseData>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    liveDataForViewToObserve.postValue(AppData.Success(response.body()!!))
                } else {
                    val message = response.message()
                    if (message.isNullOrEmpty()) {
                        liveDataForViewToObserve.postValue(AppData.Error(Exception("Unidentified error")))
                    } else {
                        liveDataForViewToObserve.postValue(AppData.Error(Exception(message)))
                    }
                }
            }

            override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                liveDataForViewToObserve.postValue(AppData.Error(Exception(t)))
            }

        })
    }

    fun getData(): MutableLiveData<AppData<PODServerResponseData>> = liveDataForViewToObserve
}
