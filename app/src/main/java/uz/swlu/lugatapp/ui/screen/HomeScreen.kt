package uz.swlu.lugatapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.swlu.lugatapp.R
import uz.swlu.lugatapp.ui.theme.LugatAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen() {

    var search by remember {
        mutableStateOf("")
    }

    LugatAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Lug’at",

                            // Lable/Small 20
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
                            onClick = { /*TODO*/ }
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
                    }
                )
            },
            containerColor = Color(0xFFF5F7F9)
        ) { pad ->
            Column(
                modifier = Modifier
                    .padding(pad)
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
                        .fillMaxWidth()
                )

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 60.dp
                    )
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
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF909DAD)
                            )

                            IconButton(
                                onClick = { /*TODO*/ }
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

                    items(20) {
                        ItemVocabulary()
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun ItemVocabulary() {
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

            }
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {

        Text(
            text = "Abceiling",
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
            text = "tikka qoya yoki baland bino devoridan tushish",
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
            text = "спуск с отвесных скал или стен высотных зданий по веревке",
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