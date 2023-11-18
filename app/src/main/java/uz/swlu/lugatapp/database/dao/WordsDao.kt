package uz.swlu.lugatapp.database.dao

import androidx.room.Dao
import androidx.room.Query
import uz.swlu.lugatapp.database.entity.Letter
import uz.swlu.lugatapp.database.entity.WordsEntity

@Dao
interface WordsDao : BaseDao<WordsEntity> {

    @Query("SELECT * FROM words")
    suspend fun getBaskets(): List<WordsEntity>?

    @Query("DELETE FROM words")
    suspend fun deleteAll()

    @Query("select count(*) from words")
    suspend fun getBasketSize(): Int

    @Query("select * from words where (english like '%' || :search || '%') or (uzbek like '%' || :search || '%') or (russian like '%' || :search || '%') ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun searchWord(search: String = "", limit: Int, offset: Int): List<WordsEntity>

    @Query("select * from words ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun searchWord(limit: Int, offset: Int): List<WordsEntity>

    @Query("select * from words where (english like :letter || '%') and (english like :search || '%') ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun searchLetterWord(
        letter: String,
        search: String = "",
        limit: Int,
        offset: Int
    ): List<WordsEntity>

    @Query("select * from words where (english like :letter || '%') ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun searchLetterWord(letter: String, limit: Int, offset: Int): List<WordsEntity>

    @Query("select lower(substr(english,1,1)) as letter, count(*) as count from words group by letter")
    suspend fun getLetter(): List<Letter>

}