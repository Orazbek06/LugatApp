package uz.swlu.lugatapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.swlu.lugatapp.database.WordsDatabase
import uz.swlu.lugatapp.database.dao.WordsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalBaseModule {

    @Singleton
    @Provides
    fun provide100KDatabase(@ApplicationContext context: Context): WordsDatabase {
        return Room.databaseBuilder(context, WordsDatabase::class.java, "words_db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideRecentDao(database: WordsDatabase): WordsDao {
        return database.wordsDao()
    }


}