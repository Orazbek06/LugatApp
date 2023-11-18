package uz.swlu.lugatapp.repository.auth

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.swlu.lugatapp.database.LetterWordsDataSource
import uz.swlu.lugatapp.database.WordsDataSource
import uz.swlu.lugatapp.database.dao.WordsDao
import uz.swlu.lugatapp.database.entity.Letter
import uz.swlu.lugatapp.database.entity.WordsEntity
import uz.swlu.lugatapp.pref.MyPref
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dao: WordsDao,
    private val pref: MyPref
) : AuthRepository {

    override fun introScreen(startBool: Boolean) {
        pref.startScreen = startBool
    }

    override fun getIntroStart(): Boolean = pref.startScreen

    override suspend fun insertWords(words: List<WordsEntity>): Flow<PagingData<WordsEntity>> {

        dao.insert(words)

        pref.startScreen = true
        return try {
            Pager(
                config = PagingConfig(
                    pageSize = 4
                ),
                initialKey = 1,
                pagingSourceFactory = { WordsDataSource(dao).create() }
            ).flow
        } catch (e: Exception) {
            flow {
                emit(PagingData.empty())
            }
        }
    }

    override fun searchWords(search: String?): Flow<PagingData<WordsEntity>> {
        return try {
            Pager(
                config = PagingConfig(
                    pageSize = 4
                ),
                initialKey = 1,
                pagingSourceFactory = { WordsDataSource(dao).create(search) }
            ).flow
        } catch (e: Exception) {
            Log.d("VVVVV", "searchWords: ${e.message}")
            flow {
                emit(PagingData.empty())
            }
        }
    }

    override fun searchLetterWords(
        letter: String,
        search: String?
    ): Flow<PagingData<WordsEntity>> {
        return try {
            Pager(
                config = PagingConfig(
                    pageSize = 4
                ),
                initialKey = 1,
                pagingSourceFactory = { LetterWordsDataSource(dao).create(letter, search) }
            ).flow
        } catch (e: Exception) {
            flow {
                emit(PagingData.empty())
            }
        }
    }

    override fun getLetters(): Flow<List<Letter>> = flow {
        val data = dao.getLetter()
        emit(data)
    }

}