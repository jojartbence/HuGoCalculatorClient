package com.jojartbence.utdijkalkulator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jojartbence.repository.Repository

class ServerUrlSetViewModel : ViewModel() {
    val serverUrl = MutableLiveData<String>()

    fun setServerUrl(url: String) {
        Repository.setUrl(url)
        serverUrl.value = url
    }

    fun getServerUrl() {
        serverUrl.value = Repository.getUrl()
    }

}
