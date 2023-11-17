package uz.swlu.lugatapp.database.datasource

import uz.swlu.lugatapp.database.entity.WordsEntity

interface WordsDS {

    suspend fun insertBasket(entity: WordsEntity)

    suspend fun insertBaskets(entity: List<WordsEntity>)

    suspend fun deleteBasket(entity: WordsEntity)

    suspend fun updateBasket(entity: WordsEntity)

    suspend fun getBaskets(): List<WordsEntity>?

    suspend fun getBasketSize(): Int

}