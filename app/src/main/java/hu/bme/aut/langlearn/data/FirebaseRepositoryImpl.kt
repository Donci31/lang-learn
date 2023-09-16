package hu.bme.aut.langlearn.data

import com.google.firebase.firestore.FirebaseFirestore
import hu.bme.aut.langlearn.domain.Deck
import hu.bme.aut.langlearn.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
) : FirebaseRepository {
    override fun getAllDecks(): Flow<Resource<List<Deck>>> = flow {
        emit(Resource.Loading())

        val decks = firestore
            .collection("decks")
            .get()
            .await()
            .map {
                it.toObject(Deck::class.java)
            }

        emit(Resource.Success(decks))
    }.catch {
        emit(Resource.Error(message = "Error fetching deck list: ${it.message}"))
    }.flowOn(Dispatchers.IO)

    override fun addDeck(deck: Deck) {
        firestore
            .collection("decks")
            .document(deck.name)
            .set(deck)
    }
}