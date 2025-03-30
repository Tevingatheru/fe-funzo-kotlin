package com.example.fe_funzo.data.model

enum class OptionType(val optionTypeName: String) {
    TRUE_FALSE(optionTypeName = "true_or_false"),
    MULTIPLE_CHOICE(optionTypeName = "multiple_choice"),;

    fun isTrueFalse(): Boolean {
        return this == TRUE_FALSE
    }

    fun isMCQ(): Boolean {
        return this == MULTIPLE_CHOICE
    }

    companion object {
        fun find(optionTypeName: String): OptionType {
            return when(optionTypeName) {
                TRUE_FALSE.optionTypeName -> TRUE_FALSE
                MULTIPLE_CHOICE.optionTypeName -> MULTIPLE_CHOICE

                else -> {
                    throw IllegalArgumentException("OptionType type: \"$optionTypeName\" does not exit")
                }
            }
        }
    }
}
