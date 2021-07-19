package com.example.gbmaterialdesign.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gbmaterialdesign.common.AppData
import com.example.gbmaterialdesign.data.model.MarsServerResponseData
import com.example.gbmaterialdesign.domain.repository.MarsRepository
import com.example.gbmaterialdesign.domain.repository.MarsRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarsViewModel : ViewModel() {
    private val marsRepository: MarsRepository = MarsRepositoryImpl()
    private val liveDataForViewToObserve: MutableLiveData<AppData<MarsServerResponseData>> =
        MutableLiveData()

    fun loadData() {
        liveDataForViewToObserve.postValue(AppData.Loading())

        marsRepository.getDataMars().enqueue(object : Callback<MarsServerResponseData> {
            override fun onResponse(
                call: Call<MarsServerResponseData>,
                response: Response<MarsServerResponseData>
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

            override fun onFailure(call: Call<MarsServerResponseData>, t: Throwable) {
                liveDataForViewToObserve.postValue(AppData.Error(Exception(t)))
            }

        })
    }

    fun getData(): MutableLiveData<AppData<MarsServerResponseData>> = liveDataForViewToObserve
}
