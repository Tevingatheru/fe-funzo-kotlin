package com.example.fe_funzo.logic.view_model

import android.util.Log
import com.example.fe_funzo.data.request.CreateSubjectRequest
import com.example.fe_funzo.data.response.CreateSubjectResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.SubjectClient
import com.example.fe_funzo.logic.service.impl.SubjectClientServiceImpl
import com.example.fe_funzo.presentation.activity.SubjectDetailsActivity
import com.example.fe_funzo.data.model.Subject

class SubjectViewModel(val subjectDetailsActivity: SubjectDetailsActivity) {
    companion object {
        private const val TAG: String = "SubjectViewModel"
    }

    suspend fun createSubject(subject: Subject): CreateSubjectResponse {
        val subjectClient: SubjectClient = RetrofitClientBuilder.build(SubjectClient::class.java)
        val subjectClientServiceImpl: SubjectClientServiceImpl =
            SubjectClientServiceImpl(subjectClient = subjectClient)
        val createSubjectRequest = CreateSubjectRequest(subject.category, subject.description, subject.name)

        try {
            return subjectClientServiceImpl.createSubject(createSubjectRequest)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to create subject: Error: $e")
            throw e
        }
    }
}
