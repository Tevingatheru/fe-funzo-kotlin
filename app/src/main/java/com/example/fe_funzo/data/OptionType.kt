package com.example.fe_funzo.data

enum class OptionType(val optionTypeName: String) {
    TRUE_FALSE(optionTypeName = "True/False"),
    MULTIPLE_CHOICE(optionTypeName = "Multiple Choice"),;

    companion object {
        fun find(optionType: String): OptionType {
            return when(optionType) {
                TRUE_FALSE.optionTypeName -> TRUE_FALSE
                MULTIPLE_CHOICE.optionTypeName -> MULTIPLE_CHOICE

                else -> {
                    throw IllegalArgumentException("OptionType type: \"$optionType\" does not exit")
                }
            }
        }
    }
}
