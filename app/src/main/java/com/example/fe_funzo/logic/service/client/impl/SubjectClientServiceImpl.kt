package com.example.fe_funzo.logic.service.client.impl

import com.example.fe_funzo.data.retrofit.request.CreateSubjectRequest
import com.example.fe_funzo.data.retrofit.response.GetAllSubjectsResponse
import com.example.fe_funzo.data.retrofit.response.SubjectResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.SubjectClient
import com.example.fe_funzo.logic.service.client.SubjectClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SubjectClientServiceImpl (private val subjectClient: SubjectClient = RetrofitClientBuilder.build(SubjectClient::class.java)):
    SubjectClientService {

    override suspend fun createSubject(createSubjectRequest: CreateSubjectRequest): SubjectResponse {
        return withContext(Dispatchers.IO) {
            subjectClient.createSubject(createSubjectRequest = createSubjectRequest)
        }
    }

    override suspend fun getAllSubjects(): GetAllSubjectsResponse {
        return withContext(Dispatchers.IO) {
            subjectClient.getAllSubjects()
        }
    }
}
