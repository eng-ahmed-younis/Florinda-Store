package com.florinda.domain.use_case.auth_use_case

import com.florinda.domain.model.Resource
import com.florinda.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LogOutUseCase constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<Resource<FirebaseUser>> = flow {
        emit(Resource.Loading)
        val user = authRepository.signOut()
        emit(Resource.Success(user!!))
    }.catch {
        emit(Resource.Error(it.message ?: "un known error"))
    }
}
