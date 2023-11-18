package uz.swlu.lugatapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.swlu.lugatapp.R

@Composable
fun AboutScreen(
    onBackPress: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { pad ->

        Box(
            modifier = Modifier
                .padding(pad)
                .fillMaxSize()
                .background(Color(0xFFF5F7F9))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF742AE5))
                        .padding(bottom = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.img_owner),
                        contentDescription = "Owner image",
                        modifier = Modifier
                            .padding(top = 64.dp)
                            .clip(CircleShape)
                    )

                    Text(
                        text = "Yakubova Noira",
                        style = TextStyle(
                            fontSize = 26.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight(510),
                            color = Color(0xFFF1EAFC),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }

                Text(
                    text = "Muallif haqida",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight(510),
                        color = Color(0xFF909DAD),
                        letterSpacing = 0.27.sp,
                    ),
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 15.dp,
                        bottom = 5.dp
                    )
                )

                Text(
                    text = "O’zbekiston davlat jahon tillari universiteti ikkinchi chet tili kafedrasida filologiya fanlari bo’yicha falsafa doktori (PhD).\nNoira Yakubova terminologiya, leksikografiya va semantika sohalari bo’yicha mutaxassis hamda ushbu sohalarga oid 40 dan ortiq ilmiy ishlar va “Dictionary of the tourism terms (English – Uzbek – Russian languages)/Turizmga oid terminlarning izohli lug’ati (Ingliz – O’zbek – Rus tillarda) nomli lug’ati” muallifi.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight(510),
                        color = Color(0xFF0A0D10),
                        letterSpacing = 0.24.sp,
                    ),
                    modifier = Modifier
                        .shadow(
                            elevation = 2.dp,
                            spotColor = Color(0x261C1F27),
                            ambientColor = Color(0x261C1F27)
                        )
                        .width(393.dp)
                        .height(282.dp)
                        .background(color = Color(0xFFFFFFFF))
                        .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp)
                )

            }

            IconButton(
                onClick = { onBackPress() },
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 6.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back button",
                    tint = Color.White
                )
            }

        }

    }
}