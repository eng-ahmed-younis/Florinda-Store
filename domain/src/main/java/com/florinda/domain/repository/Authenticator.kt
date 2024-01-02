package com.florinda.domain.repository

import com.google.firebase.auth.FirebaseUser

interface Authenticator {

    suspend fun registerWithEmailPassword(email:String, password:String) : FirebaseUser?

    suspend fun loginWithEmailPassword(email: String, password: String):FirebaseUser?

    fun signOut() : FirebaseUser?

    fun getUser() : FirebaseUser?

    suspend fun sendPasswordReset(email :String)
}