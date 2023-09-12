package hu.bme.aut.langlearn.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.langlearn.data.AuthRepository
import hu.bme.aut.langlearn.data.AuthRepositoryImpl
import hu.bme.aut.langlearn.data.FirebaseRepository
import hu.bme.aut.langlearn.data.FirebaseRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun providesAuthRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository =
        AuthRepositoryImpl(firebaseAuth)

    @Provides
    @Singleton
    fun providesFirestore() = Firebase.firestore

    @Provides
    @Singleton
    fun providesFirestoreRepositoryImpl(firestore: FirebaseFirestore): FirebaseRepository =
        FirebaseRepositoryImpl(firestore)
}