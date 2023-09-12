package hu.bme.aut.langlearn.data

import com.google.firebase.firestore.FirebaseFirestore
import hu.bme.aut.langlearn.data.deck_screen.Deck
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): FirebaseRepository {
    override fun getAllDecks() {

    }

    override fun addDeck(deck: Deck) {
        firestore
            .collection("decks")
            .document(deck.name)
            .set(deck)
    }
}