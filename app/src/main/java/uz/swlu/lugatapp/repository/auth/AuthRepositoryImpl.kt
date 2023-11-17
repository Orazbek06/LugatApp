package uz.swlu.lugatapp.repository.auth

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.swlu.lugatapp.database.WordsDataSource
import uz.swlu.lugatapp.database.dao.WordsDao
import uz.swlu.lugatapp.database.entity.WordsEntity
import uz.swlu.lugatapp.pref.MyPref
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dao: WordsDao,
    private val pref: MyPref
) :
    AuthRepository {

    override fun introScreen(startBool: Boolean) {
        pref.startScreen = startBool
    }

    override fun getIntroStart(): Boolean = pref.startScreen

    override fun insertWords(words: List<WordsEntity>): Flow<Result<String>> = flow {
        dao.insert(words)
        pref.startScreen = true
        emit(Result.success("Success!"))
    }.catch {
        emit(
            Result.failure(it)
        )
    }.flowOn(Dispatchers.IO)

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
            flow {
                emit(PagingData.empty())
            }
        }
    }

}