package com.example.fe_funzo.logic.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.response.UserCountResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.UserClient
import kotlinx.coroutines.runBlocking

class BIViewModel : ViewModel() {
    fun getUserCount(): MutableState<Int> {
        val userClient: UserClient = RetrofitClientBuilder.build(UserClient::class.java)
        val userCount: MutableState<Int> = mutableIntStateOf(0)

        runBlocking {
            val response: UserCountResponse = userClient.getUserCount()
            userCount.value = response.totalCount
        }
        return userCount
    }

}
