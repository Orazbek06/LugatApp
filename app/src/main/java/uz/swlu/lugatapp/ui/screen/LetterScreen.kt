package uz.swlu.lugatapp.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.launch
import uz.swlu.lugatapp.database.entity.WordsEntity
import uz.swlu.lugatapp.ui.viewmodel.MainViewModel
import uz.swlu.lugatapp.ui.viewmodel.WordsViewModelImpl

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun LetterScreen(
    letter: String,
    viewModel: MainViewModel = hiltViewModel<WordsViewModelImpl>(),
    onBackPress: () -> Unit
) {

    var search by remember {
        mutableStateOf("")
    }

    val words = viewModel.letterWords.collectAsLazyPagingItems()

    var word by remember {
        mutableStateOf(WordsEntity(0, "", "", ""))
    }

    val modalState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            ItemScreen(
                word,
                onBackPress = {
                    scope.launch { modalState.hide() }
                }
            )
        },
        sheetShape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp
        ),
        sheetState = modalState,
        sheetBackgroundColor = Color(0xFFF5F7F9)
    ) {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = letter,
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight(510),
                                color = Color(0xFF0A0D10),
                                letterSpacing = 0.2.sp,
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { onBackPress() }) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "Back button",
                                tint = Color.Black
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
                )
            },
            containerColor = Color(0xFFF5F7F9)
        ) { pad ->
            Column(
                modifier = Modifier
                    .padding(pad)
                    .fillMaxSize()
            ) {

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 60.dp,
                        top = 10.dp
                    )
                ) {
                    items(words) {
                        it?.let {
                            ItemVocabulary(
                                data = it,
                                onWordClick = {
                                    word = it
                                    scope.launch {
                                        modalState.show()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    BackHandler {
        if (modalState.isVisible) {
            scope.launch {
                modalState.hide()
            }
        } else onBackPress()
    }

}