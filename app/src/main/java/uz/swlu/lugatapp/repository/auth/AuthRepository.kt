package uz.swlu.lugatapp.repository.auth

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.swlu.lugatapp.database.entity.Letter
import uz.swlu.lugatapp.database.entity.WordsEntity

interface AuthRepository {

    fun introScreen(startBool: Boolean)

    fun getIntroStart(): Boolean

    suspend fun insertWords(words: List<WordsEntity>): Flow<PagingData<WordsEntity>>

    fun searchWords(search: String? = null): Flow<PagingData<WordsEntity>>

    fun searchLetterWords(letter: String, search: String? = null): Flow<PagingData<WordsEntity>>

    fun getLetters(): Flow<List<Letter>>
}