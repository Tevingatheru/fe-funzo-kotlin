package com.example.fe_funzo.logic.service.impl

import com.example.fe_funzo.data.room.request.CreateSubjectRequest
import com.example.fe_funzo.data.room.response.GetAllSubjectsResponse
import com.example.fe_funzo.data.room.response.SubjectResponse
import com.example.fe_funzo.infa.client.retrofit.client.SubjectClient
import com.example.fe_funzo.logic.service.SubjectClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SubjectClientServiceImpl (private val subjectClient: SubjectClient): SubjectClientService {

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
