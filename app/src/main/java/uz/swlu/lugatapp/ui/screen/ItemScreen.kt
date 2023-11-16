package uz.swlu.lugatapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.swlu.lugatapp.R
import uz.swlu.lugatapp.ui.theme.LugatAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ItemScreen() {
    LugatAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "So‘z tarjimasi",

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
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "Action",
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_save),
                                contentDescription = "Action",
                                tint = Color(0xFF07090B)
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
                    .padding(
                        horizontal = 10.dp,
                        vertical = 20.dp
                    )
            ) {

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .background(
                            Color.White,
                            RoundedCornerShape(15.dp)
                        )
                        .padding(
                            vertical = 10.dp,
                            horizontal = 20.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_english),
                        contentDescription = "English"
                    )

                    Text(
                        text = "Abceiling",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight(590),
                            color = Color(0xFF742AE5),
                        )
                    )

                }

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_uzbek),
                        contentDescription = "Uzbek"
                    )

                    Text(
                        text = "Tikka qoya yoki baland bino devoridan tushish",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(510),
                            color = Color(0xFF0A0D10),
                            letterSpacing = 0.27.sp,
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_russian),
                        contentDescription = "Russian"
                    )

                    Text(
                        text = "Спуск с отвесных скал или стен высотных зданий по веревке",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(510),
                            color = Color(0xFF0A0D10),
                            letterSpacing = 0.27.sp,
                        )
                    )
                }

            }
        }
    }
}
