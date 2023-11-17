package uz.swlu.lugatapp.database

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import uz.swlu.lugatapp.database.dao.WordsDao
import uz.swlu.lugatapp.database.entity.WordsEntity
import javax.inject.Inject

class WordsDataSource @Inject constructor(
    private val dao: WordsDao
) : PagingSource<Int, WordsEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WordsEntity> {
        val page = params.key ?: 0

        return try {
            val entities = if(search.isNullOrBlank())dao.searchWord(20,page*20)
                else dao.searchWord(search ?: "", 20, page * 20)

            // simulate page loading
            if (page != 0) delay(1000)

            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, WordsEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    fun create(search: String? = null): WordsDataSource {
        return WordsDataSource(dao).apply {
            this.search = search
        }
    }

    private var search: String? = null

}