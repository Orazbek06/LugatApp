package uz.swlu.lugatapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.swlu.lugatapp.database.dao.WordsDao
import uz.swlu.lugatapp.database.entity.WordsEntity

@Database(
    entities = [WordsEntity::class],
    version = 2
)
abstract class WordsDatabase : RoomDatabase() {

    abstract fun wordsDao(): WordsDao

}