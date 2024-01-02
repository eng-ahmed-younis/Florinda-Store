package com.florinda.domain.use_case.auth_use_case

import com.florinda.domain.model.Resource
import com.florinda.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ResetPasswordUseCase constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading)
        val result = authRepository.sendResetPassword(email)
        if (result) {
            emit(Resource.Success("reset email sent"))
        } else {
            emit(Resource.Success("could not send password reset"))
        }
    }.catch {
        emit(Resource.Error(it.message ?: "un known error"))
    }
}
