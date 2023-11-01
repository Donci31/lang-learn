package hu.bme.aut.langlearn.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.languageid.LanguageIdentificationOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.langlearn.data.remote.OpenAIAPI
import hu.bme.aut.langlearn.domain.repositories.AuthRepository
import hu.bme.aut.langlearn.data.repositories.AuthRepositoryImpl
import hu.bme.aut.langlearn.domain.repositories.DeckRepository
import hu.bme.aut.langlearn.data.repositories.DeckRepositoryImpl
import hu.bme.aut.langlearn.domain.repositories.ProgressRepository
import hu.bme.aut.langlearn.data.repositories.ProgressRepositoryImpl
import hu.bme.aut.langlearn.domain.repositories.SentenceRepository
import hu.bme.aut.langlearn.data.repositories.SentenceRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLanguageIdentifier() =
        LanguageIdentification.getClient(
            LanguageIdentificationOptions.Builder()
                .setConfidenceThreshold(0.1f)
                .build()
        )

    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun providesFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun providesAuthRepositoryImpl(firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore): AuthRepository =
        AuthRepositoryImpl(firebaseAuth, firestore)

    @Provides
    @Singleton
    fun providesDeckRepositoryImpl(firestore: FirebaseFirestore): DeckRepository =
        DeckRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun providesUserRepositoryImpl(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore,
    ): ProgressRepository =
        ProgressRepositoryImpl(auth, firestore)

    @Provides
    @Singleton
    fun providesSentenceRepositoryImpl(openAIAPI: OpenAIAPI): SentenceRepository =
        SentenceRepositoryImpl(openAIAPI)
}