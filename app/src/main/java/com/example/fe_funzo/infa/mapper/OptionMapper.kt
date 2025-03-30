package com.example.fe_funzo.infa.mapper

import com.example.fe_funzo.data.model.Option
import com.example.fe_funzo.data.model.OptionType
import com.example.fe_funzo.data.retrofit.response.OptionResponse

object OptionMapper {
    fun mapFromOptionResponse(optionResponse: OptionResponse): Option {
        return Option(
            optionA = optionResponse.optionA,
            optionB = optionResponse.optionB,
            optionC = optionResponse.optionC,
            optionD = optionResponse.optionD,
            correctOption = optionResponse.correctOption,
            optionType = OptionType.find(optionTypeName = optionResponse.type!!),
            code = optionResponse.code,
        )
    }
}
