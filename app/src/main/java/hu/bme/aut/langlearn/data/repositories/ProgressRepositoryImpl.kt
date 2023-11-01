package hu.bme.aut.langlearn.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import hu.bme.aut.langlearn.domain.entities.DeckPractice
import hu.bme.aut.langlearn.domain.entities.Practice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProgressRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : ProgressRepository {

    private val user: String
        get() = auth.uid!!

    override fun getAllPractices(): Flow<List<DeckPractice>> =
        firestore
            .collection("users")
            .document(user)
            .collection("deckProgress")
            .snapshots()
            .map {
                it.toObjects()
            }

    override fun addPractice(deckId: String, practice: Practice) {
        val documentRef = firestore
            .collection("users")
            .document(user)
            .collection("deckProgress")
            .document(deckId)

        documentRef
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    documentRef.update("practices", FieldValue.arrayUnion(practice))
                } else {
                    documentRef.set(DeckPractice(deckId = deckId, practices = listOf(practice)))
                }
            }

        firestore
            .collection("users")
            .document(user)
            .update("practice_day", FieldValue.arrayUnion(practice.date))
    }
}