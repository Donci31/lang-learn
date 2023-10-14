package hu.bme.aut.langlearn.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import hu.bme.aut.langlearn.domain.DeckPractice
import hu.bme.aut.langlearn.domain.Practice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProgressRepositoryImpl @Inject constructor(
    auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : ProgressRepository {

    private val user = auth.uid!!

    override fun getAllPractices(): Flow<List<DeckPractice>> =
        firestore
            .collection("users")
            .document(user)
            .collection("deckProgress")
            .snapshots()
            .map {
                it.toObjects()
            }

    override fun createDeckPractice(deckId: String) {
        val collectionRef = firestore
            .collection("users")
            .document(user)
            .collection("deckProgress")

        collectionRef
            .document(deckId)
            .get()
            .addOnSuccessListener { document ->
                if (!document.exists()) {
                    collectionRef
                        .document(deckId)
                        .set(
                            DeckPractice(
                                deckId = deckId
                            )
                        )
                }
            }
    }

    override fun addPractice(practice: Practice, deckId: String) {
        firestore
            .collection("users")
            .document(user)
            .collection("deckProgress")
            .document(deckId)
            .update("practices", FieldValue.arrayUnion(practice))
    }
}