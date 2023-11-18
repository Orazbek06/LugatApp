package uz.swlu.lugatapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.apache.poi.ss.usermodel.Workbook
import uz.swlu.lugatapp.database.entity.Letter
import uz.swlu.lugatapp.database.entity.WordsEntity

interface MainViewModel {

    val tableType: LiveData<Int>

    val lastSearch: LiveData<String>

    val firstInit: LiveData<Boolean>

    val words: Flow<PagingData<WordsEntity>>

    val letterWords: Flow<PagingData<WordsEntity>>

    val progressFlow: Flow<Boolean>

    val hasLoadedWords: LiveData<Boolean>

    val lazyStateValue: LiveData<Pair<Int, Int>>

    val letters: LiveData<List<Letter>>

    fun insertWords(workbook: Workbook)

    fun getWords(search: String)

    fun getLetterWords(
        letter: String,
        search: String
    )

    fun changeTableType()

    fun saveState(index: Int, offset: Int)

    fun getLetters()

}