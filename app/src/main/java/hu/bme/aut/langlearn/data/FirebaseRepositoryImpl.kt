package hu.bme.aut.langlearn.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import hu.bme.aut.langlearn.domain.Deck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : FirebaseRepository {
    override fun getAllDecks(): Flow<List<Deck>> =
        firestore
            .collection("decks")
            .snapshots()
            .map {
                it.toObjects()
            }

    override fun addDeck(deck: Deck) {
        firestore
            .collection("decks")
            .document(deck.id)
            .set(deck)
    }
}