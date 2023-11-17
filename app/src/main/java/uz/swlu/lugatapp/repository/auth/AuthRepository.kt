package uz.swlu.lugatapp.repository.auth

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.swlu.lugatapp.database.entity.WordsEntity

interface AuthRepository {
    fun introScreen(startBool: Boolean)

    fun getIntroStart(): Boolean

    fun insertWords(words: List<WordsEntity>): Flow<Result<String>>

    fun searchWords(search: String? = null): Flow<PagingData<WordsEntity>>
}