package uz.swlu.lugatapp.database

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.swlu.lugatapp.database.dao.WordsDao
import uz.swlu.lugatapp.database.entity.WordsEntity
import javax.inject.Inject

class LetterWordsDataSource @Inject constructor(
    private val dao: WordsDao
) : PagingSource<Int, WordsEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WordsEntity> {
        val page = params.key ?: 0

        return try {

            val entities = dao.searchLetterWord(letter ?: "", search ?: "", 20, page * 20)
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

    fun create(letter: String, search: String? = null): LetterWordsDataSource {
        return LetterWordsDataSource(dao).apply {
            this.search = search
            this.letter = letter
        }
    }

    private var search: String? = null

    private var letter: String? = null

}