package com.florinda.data.data_source.remote.firebase

import android.util.Log
import com.florinda.domain.repository.Authenticator
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

class FirebaseAuthenticator(
    private val firebaseAuth: FirebaseAuth
) : Authenticator {

    override suspend fun registerWithEmailPassword(email: String, password: String): FirebaseUser? {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return Firebase.auth.currentUser
    }

    override suspend fun loginWithEmailPassword(email: String, password: String): FirebaseUser? {
        Log.i("FirebaseAuthenticator", Firebase.auth.currentUser?.email.toString())
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.i("FirebaseAuthenticator", "success")
            } else {
                Log.i("FirebaseAuthenticator", "fail")
            }
        }.await()
        Log.i("FirebaseAuthenticator", Firebase.auth.currentUser?.email.toString())
        return Firebase.auth.currentUser
    }

    override fun signOut(): FirebaseUser? {
        firebaseAuth.signOut()
        return Firebase.auth.currentUser
    }

    /* override fun getUser(): FirebaseUser? {
         return firebaseAuth.currentUser
     }*/

    override fun getUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }


    override suspend fun sendPasswordReset(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).await()
    }
}