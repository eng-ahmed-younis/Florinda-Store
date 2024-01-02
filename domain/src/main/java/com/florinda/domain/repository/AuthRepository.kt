package com.florinda.domain.repository

import com.google.firebase.auth.FirebaseUser

/*interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}*/

interface AuthRepository {

    suspend fun loginWithEmailPassword(email:String, password:String): FirebaseUser?

    suspend fun registerWithEmailPassword(email: String, password: String): FirebaseUser?

    fun signOut() : FirebaseUser?

    fun getCurrentUser() : FirebaseUser?

    suspend fun sendResetPassword(email : String) : Boolean
}