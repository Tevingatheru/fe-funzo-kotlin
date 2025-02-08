package com.example.fe_funzo.dto

import com.example.fe_funzo.view.SignIn
import com.example.fe_funzo.view_model.SignInViewModel

class SignInRequest (
    val signInVM: SignInViewModel = SignInViewModel(),

    val signInContext: SignIn
)
{

}
