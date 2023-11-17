package uz.swlu.lugatapp.database.datasource

import uz.swlu.lugatapp.database.dao.WordsDao
import uz.swlu.lugatapp.database.entity.WordsEntity
import javax.inject.Inject

class WordsDataSourceImpl @Inject constructor(private val basketDao: WordsDao) :
    WordsDS {
    override suspend fun insertBasket(entity: WordsEntity) {
        return basketDao.insert(entity)
    }

    override suspend fun insertBaskets(entity: List<WordsEntity>) {
        return basketDao.insert(entity)
    }

    override suspend fun deleteBasket(entity: WordsEntity) {
        basketDao.delete(entity)
    }

    override suspend fun updateBasket(entity: WordsEntity) {
        basketDao.update(entity)
    }

    override suspend fun getBaskets(): List<WordsEntity>? {
        return basketDao.getBaskets()
    }


    override suspend fun getBasketSize(): Int {
        return basketDao.getBasketSize()
    }

}