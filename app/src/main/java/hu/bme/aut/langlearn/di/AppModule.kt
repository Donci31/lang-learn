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
import hu.bme.aut.langlearn.data.remote.OpenAIAPI
import hu.bme.aut.langlearn.data.repositories.AuthRepository
import hu.bme.aut.langlearn.data.repositories.AuthRepositoryImpl
import hu.bme.aut.langlearn.data.repositories.DeckRepository
import hu.bme.aut.langlearn.data.repositories.DeckRepositoryImpl
import hu.bme.aut.langlearn.data.repositories.ProgressRepository
import hu.bme.aut.langlearn.data.repositories.ProgressRepositoryImpl
import hu.bme.aut.langlearn.data.repositories.SentenceRepository
import hu.bme.aut.langlearn.data.repositories.SentenceRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun providesFirestore() = Firebase.firestore

    @Provides
    @Singleton
    fun providesAuthRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository =
        AuthRepositoryImpl(firebaseAuth)

    @Provides
    @Singleton
    fun providesDeckRepositoryImpl(firestore: FirebaseFirestore): DeckRepository =
        DeckRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun providesUserRepositoryImpl(auth: FirebaseAuth, firestore: FirebaseFirestore): ProgressRepository =
        ProgressRepositoryImpl(auth, firestore)

    @Provides
    @Singleton
    fun providesSentenceRepositoryImpl(openAIAPI: OpenAIAPI): SentenceRepository =
        SentenceRepositoryImpl(openAIAPI)
}