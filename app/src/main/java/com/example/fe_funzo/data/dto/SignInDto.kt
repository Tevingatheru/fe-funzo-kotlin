package com.example.fe_funzo.data.dto

import com.example.fe_funzo.presentation.view.SignIn
import com.example.fe_funzo.logic.view_model.SignInViewModel

class SignInDto (
    val signInVM: SignInViewModel = SignInViewModel(),

    val signInContext: SignIn
)
{

}
