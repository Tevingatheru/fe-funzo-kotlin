package com.example.fe_funzo.logic.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fe_funzo.data.room.request.CreateSubjectRequest
import com.example.fe_funzo.data.room.response.SubjectResponse
import com.example.fe_funzo.infa.client.retrofit.RetrofitClientBuilder
import com.example.fe_funzo.infa.client.retrofit.client.SubjectClient
import com.example.fe_funzo.logic.service.impl.SubjectClientServiceImpl
import com.example.fe_funzo.data.model.Subject
import com.example.fe_funzo.data.room.response.GetAllSubjectsResponse

import kotlinx.coroutines.runBlocking

class SubjectViewModel(): ViewModel() {

    private lateinit var subjectCode: String
    private var selectedSubjectName: MutableState<String> = mutableStateOf("")
    private var selectedSubjectCode: MutableState<String> = mutableStateOf("")

    companion object {
        private const val TAG: String = "SubjectViewModel"
    }

    fun getSelectedSubjectCode(): String {
        return selectedSubjectCode.value
    }

    fun setSelectedSubjectCode(code: String) {
        selectedSubjectCode.value = code
    }

    fun getSubjectList () :  List<Subject>{
        var subjectList : List<Subject>
        val subjectClient: SubjectClient = RetrofitClientBuilder.build(SubjectClient::class.java)
        val subjectClientServiceImpl: SubjectClientServiceImpl = SubjectClientServiceImpl(subjectClient = subjectClient)

        return runBlocking {
            subjectList = mapToSubjectList(getAllSubjects = subjectClientServiceImpl.getAllSubjects())
            Log.i(TAG, "SubjectList: $subjectList, \n List count: SubjectList: ${subjectList.count()}")
            return@runBlocking subjectList
        }
    }

    private fun mapToSubjectList(getAllSubjects: GetAllSubjectsResponse): List<Subject> {
        return getAllSubjects.subjects.map { responseSubject ->
            Subject(
                id = null,
                code = responseSubject.code,
                name = responseSubject.name,
                category = responseSubject.category,
                description = responseSubject.description
            )
        }
    }

    fun getSubjectCode() :String {
        return this.subjectCode
    }

    fun setSubjectCode(subjectCode:  String ){
        this.subjectCode = subjectCode
    }

    fun selectASubject(
        subject: Subject,
        setExpanded: (Boolean) -> Unit,
        expanded: Boolean
    ) {
        setExpanded(false)
        setSubjectName(name = subject.name)
        Log.i(com.example.fe_funzo.presentation.form.TAG, "Subject has been selected. \nSubject: $subject.\nExpanded:$expanded")
    }

    fun setSubjectName(name: String) {
        selectedSubjectName.value = name
    }

    fun getSelectedSubjectName(): String {
        return selectedSubjectName.value
    }

    suspend fun createSubject(subject: Subject): SubjectResponse {
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
