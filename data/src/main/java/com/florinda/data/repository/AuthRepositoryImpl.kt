package com.florinda.data.repository

import com.florinda.domain.repository.AuthRepository
import com.florinda.domain.repository.Authenticator
import com.google.firebase.auth.FirebaseUser


class AuthRepositoryImpl (
    private val authenticator : Authenticator
) : AuthRepository {

    override suspend fun loginWithEmailPassword(email: String, password: String): FirebaseUser? {
        return authenticator.loginWithEmailPassword(email , password)
    }

    override suspend fun registerWithEmailPassword(email: String, password: String): FirebaseUser? {
        return authenticator.registerWithEmailPassword(email , password)
    }

    override fun signOut(): FirebaseUser? {
        return authenticator.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return authenticator.getUser()
    }

    override suspend fun sendResetPassword(email: String): Boolean {
        authenticator.sendPasswordReset(email)
        return true
    }

}