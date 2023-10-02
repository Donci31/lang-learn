package hu.bme.aut.langlearn.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import hu.bme.aut.langlearn.domain.Deck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : FirestoreRepository {
    override fun getAllDecks(): Flow<List<Deck>> =
        firestore
            .collection("decks")
            .snapshots()
            .map {
                it.toObjects()
            }

    override fun getDeck(deckId: String): Flow<Deck?> =
        firestore
            .collection("decks")
            .document(deckId)
            .snapshots()
            .map {
                it.toObject<Deck>()
            }

    override fun getDeckIdsAndNames(): Flow<List<Pair<String, String>>> =
        getAllDecks().map { deckList ->
            deckList.map { deck ->
                Pair(deck.id, deck.name)
            }
        }

    override fun addDeck(deck: Deck) {
        firestore
            .collection("decks")
            .document(deck.id)
            .set(deck)
    }
}