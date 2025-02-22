package com.example.fe_funzo.logic.service.impl

import com.example.fe_funzo.data.request.CreateSubjectRequest
import com.example.fe_funzo.data.response.CreateSubjectResponse
import com.example.fe_funzo.infa.client.retrofit.client.SubjectClient
import com.example.fe_funzo.logic.service.SubjectClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SubjectClientServiceImpl (private val subjectClient: SubjectClient): SubjectClientService {

    override suspend fun createSubject(createSubjectRequest: CreateSubjectRequest): CreateSubjectResponse {
        return withContext(Dispatchers.IO) {
            subjectClient.createSubject(createSubjectRequest = createSubjectRequest)
        }
    }
}
