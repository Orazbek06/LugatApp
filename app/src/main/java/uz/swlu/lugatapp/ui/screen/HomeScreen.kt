package uz.swlu.lugatapp.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.launch
import org.apache.poi.ss.usermodel.WorkbookFactory
import uz.swlu.lugatapp.R
import uz.swlu.lugatapp.database.entity.WordsEntity
import uz.swlu.lugatapp.ui.viewmodel.MainViewModel
import uz.swlu.lugatapp.ui.viewmodel.WordsViewModelImpl

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel<WordsViewModelImpl>(),
    onLetterClick: (String) -> Unit,
    onAboutClick: () -> Unit
) {

    val context = LocalContext.current
    val focus = LocalFocusManager.current

    var search by remember {
        mutableStateOf("")
    }

    val tableType by viewModel.tableType.observeAsState(initial = 1)

    val words = viewModel.words.collectAsLazyPagingItems()

    val progress by viewModel.progressFlow.collectAsState(initial = false)

    val firstInit by viewModel.firstInit.observeAsState(initial = false)

    val hasLoadedWords by viewModel.hasLoadedWords.observeAsState(initial = false)

    val lastSearch by viewModel.lastSearch.observeAsState(initial = "")

    val modalState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    var word by remember {
        mutableStateOf(WordsEntity(0, "", "", ""))
    }

    val scope = rememberCoroutineScope()

    val lazyListState = rememberLazyListState()

    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(key1 = lastSearch) {
        search = lastSearch
    }

    val alphabet = remember {
        mutableStateListOf(
            "Aa",
            "Bb",
            "Cc",
            "Dd",
            "Ee",
            "Ff",
            "Gg",
            "Hh",
            "Ii",
            "Jj",
            "Kk",
            "Ll",
            "Mm",
            "Nn",
            "Oo",
            "Pp",
            "Qq",
            "Rr",
            "Ss",
            "Tt",
            "Uu",
            "Vv",
            "Ww",
            "Xx",
            "Yy",
            "Zz",
        )
    }

    fun insertWords() {
        scope.launch {
            val file = context.resources.openRawResource(R.raw.words)

            val excel = WorkbookFactory.create(file)

            viewModel.insertWords(excel)
        }
    }

    LaunchedEffect(key1 = firstInit) {
        if (!firstInit) {
            insertWords()
        }
    }

    LaunchedEffect(key1 = hasLoadedWords) {
        if (!hasLoadedWords) {
            viewModel.getWords("")
        }
    }

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
                            text = "Lug’at",
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight(510),
                                color = Color(0xFF0A0D10),
                                letterSpacing = 0.2.sp,
                            )
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = { onAboutClick() }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.AccountCircle,
                                contentDescription = "Action",
                                modifier = Modifier
                                    .size(40.dp)
                                    .border(
                                        (0.7).dp, Color(0xFF333333),
                                        CircleShape
                                    )
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
                )
            },
            containerColor = Color(0xFFF5F7F9)
        ) { pad ->
            Box(
                modifier = Modifier
                    .padding(pad)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    TextField(
                        value = search,
                        onValueChange = {
                            search = it
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color(0xFF546881)
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Qidiruv...",
                                fontSize = 19.sp
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            disabledTextColor = Color.Black,
                            errorTextColor = Color.Black,
                            disabledPlaceholderColor = Color(
                                0xFF909DAD
                            ),
                            errorPlaceholderColor = Color(
                                0xFF909DAD
                            ),
                            focusedPlaceholderColor = Color(
                                0xFF909DAD
                            ),
                            unfocusedPlaceholderColor = Color(
                                0xFF909DAD
                            ),
                            cursorColor = Color.Black,
                            errorIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.White,
                            errorContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                        ),
                        shape = RoundedCornerShape(12.dp),
                        textStyle = TextStyle(
                            fontSize = 19.sp
                        ),
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            )
                            .fillMaxWidth(),
                        singleLine = true,
                        maxLines = 1,
                        enabled = tableType == 1,
                        keyboardActions = KeyboardActions(
                            onDone = {
                                viewModel.getWords(search)
                                focus.clearFocus()
                            },
                            onSend = {
                                viewModel.getWords(search)
                                focus.clearFocus()
                            },
                            onSearch = {
                                viewModel.getWords(search)
                                focus.clearFocus()
                            },
                        )
                    )

                    if (tableType == 1)
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 60.dp
                            ),
                            state = lazyListState
                        ) {
                            item {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Ro'yhat shaklida",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color(0xFF909DAD)
                                    )

                                    IconButton(
                                        onClick = { viewModel.changeTableType() }
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_cards),
                                            contentDescription = "Card",
                                            modifier = Modifier
                                                .size(36.dp)
                                                .background(
                                                    Color.White,
                                                    CircleShape
                                                )
                                                .padding(8.dp)
                                        )
                                    }

                                }
                            }
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
                    else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(
                                start = 16.dp,
                                end = 16.dp,
                                bottom = 60.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(7.dp),
                            horizontalArrangement = Arrangement.spacedBy(7.dp),
                            state = lazyGridState
                        ) {
                            item(span = {
                                GridItemSpan(2)
                            }) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Alifbo shaklida",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color(0xFF909DAD)
                                    )

                                    IconButton(
                                        onClick = { viewModel.changeTableType() }
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_lists),
                                            contentDescription = "Card",
                                            modifier = Modifier
                                                .size(36.dp)
                                                .background(
                                                    Color.White,
                                                    CircleShape
                                                )
                                                .padding(8.dp)
                                        )
                                    }

                                }
                            }

                            items(alphabet) {
                                ItemLetter(
                                    letter = it,
                                    onLetterClick = { str ->
                                        onLetterClick(str)
                                    }
                                )
                            }

                        }
                    }
                }

                if (progress) CircularProgressIndicator(
                    color = Color.Black, modifier = Modifier.align(Alignment.Center)
                )

            }
        }
    }

    BackHandler {
        if (modalState.isVisible) {
            scope.launch {
                modalState.hide()
            }
        }
    }
}

@Composable
fun ItemVocabulary(
    data: WordsEntity,
    onWordClick: (WordsEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Color.White,
                RoundedCornerShape(20.dp)
            )
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = rememberRipple()
            ) {
                onWordClick(data)
            }
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {

        Text(
            text = data.english,
            style = TextStyle(
                fontSize = 22.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight(510),
                color = Color(0xFF742AE5),
                letterSpacing = 0.22.sp,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = data.russian,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight(510),
                color = Color(0xFF0A0D10),
                letterSpacing = 0.24.sp,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = data.uzbek,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight(510),
                color = Color(0xFF909DAD),
                letterSpacing = 0.24.sp,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Composable
fun ItemLetter(
    letter: String,
    onLetterClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .shadow(
                elevation = 14.dp,
                spotColor = Color(0x33D7DEE5),
                ambientColor = Color(0x33D7DEE5)
            )
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                color = Color(0xFF742AE5),
                shape = RoundedCornerShape(size = 20.dp)
            )
            .clickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = rememberRipple()
            ) {
                onLetterClick(letter)
            }
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = letter,
            style = TextStyle(
                fontSize = 40.sp,
                lineHeight = 50.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF0A0D10),
            ),
            modifier = Modifier.padding(5.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "List",
                modifier = Modifier
                    .size(34.dp)
                    .background(
                        Color(0xFF5D22B7),
                        CircleShape
                    )
                    .padding(7.dp),
                tint = Color(0xFFF1EAFC)
            )

            Text(
                text = "120 ta so‘z",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight(510),
                    color = Color(0xFFF1EAFC),
                    letterSpacing = 0.24.sp,
                )
            )

        }
    }
}
