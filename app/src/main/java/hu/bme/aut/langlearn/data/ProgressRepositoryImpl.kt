package hu.bme.aut.langlearn.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import hu.bme.aut.langlearn.domain.DeckPractice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProgressRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : ProgressRepository {

    override fun getAllPractices(): Flow<List<DeckPractice>> =
        firestore
            .collection("users")
            .document(auth.uid!!)
            .collection("deckProgress")
            .snapshots()
            .map {
                it.toObjects()
            }
}