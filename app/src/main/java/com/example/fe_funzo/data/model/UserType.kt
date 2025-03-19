package com.example.fe_funzo.data.model

enum class UserType(val type: String? = null) {
    ADMINISTRATOR(type = "administrator"),
    STUDENT(type = "student"),
    TEACHER(type = "teacher");

    fun isStudent(): Boolean {
        return this == STUDENT
    }

    fun isTeacher(): Boolean {
        return this == TEACHER
    }

    fun isAdmin(): Boolean {
        return this == ADMINISTRATOR
    }

    companion object {
        fun find(userType: String): UserType {
            return when(userType) {
                ADMINISTRATOR.type -> ADMINISTRATOR
                STUDENT.type -> STUDENT
                TEACHER.type -> TEACHER
                else -> {
                    throw IllegalArgumentException("User type: \"$userType\" does not exit")
                }
            }
        }
    }
}
