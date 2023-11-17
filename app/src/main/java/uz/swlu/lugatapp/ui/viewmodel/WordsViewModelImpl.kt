package uz.swlu.lugatapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.apache.poi.ss.usermodel.Workbook
import uz.swlu.lugatapp.app.eventValueFlow
import uz.swlu.lugatapp.database.entity.WordsEntity
import uz.swlu.lugatapp.pref.MyPref
import uz.swlu.lugatapp.repository.auth.AuthRepository
import javax.inject.Inject

@HiltViewModel
class WordsViewModelImpl @Inject constructor(
    private val repos: AuthRepository,
    private val pref: MyPref
) : MainViewModel, ViewModel() {

    override val firstInit = MutableLiveData(pref.startScreen)

    override val hasLoadedWords = MutableLiveData(false)

    override val progressFlow = eventValueFlow<Boolean>()

    override val tableType = MutableLiveData(1)

    override val lastSearch = MutableLiveData("")

    override val words = eventValueFlow<PagingData<WordsEntity>>()

    init {
        firstInit.value = pref.startScreen
        tableType.value = pref.tableType
    }

    override fun insertWords(workbook: Workbook) {
        viewModelScope.launch {
            progressFlow.emit(true)

            val sheet = workbook.getSheetAt(1)

            val totalCount = sheet.lastRowNum

            val list = ArrayList<WordsEntity>()

            for (i in 0..totalCount) {
                list.add(
                    WordsEntity(
                        0,
                        sheet.getRow(i).getCell(0).stringCellValue,
                        sheet.getRow(i).getCell(1).stringCellValue,
                        sheet.getRow(i).getCell(2).stringCellValue,
                    )
                )
            }

            repos.insertWords(list).onEach {
                it.onSuccess {
                    firstInit.value = true
                    hasLoadedWords.value = false
                }
                delay(1000)
                progressFlow.emit(false)
            }.launchIn(viewModelScope)
        }
    }

    override fun getWords(search: String) {
        Log.d("VVVVV", "getWords: $search")
        viewModelScope.launch {
            progressFlow.emit(true)
            lastSearch.value = search
            repos.searchWords(search).cachedIn(viewModelScope).onEach {
                words.emit(it)
                hasLoadedWords.value = true
                progressFlow.emit(false)
            }.cachedIn(viewModelScope).launchIn(viewModelScope)
        }
    }

    override fun changeTableType() {
        viewModelScope.launch {
            val table = 3 - pref.tableType
            pref.tableType = table
            tableType.value = table
        }
    }


}