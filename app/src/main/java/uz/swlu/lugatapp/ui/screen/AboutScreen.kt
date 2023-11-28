package uz.swlu.lugatapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.swlu.lugatapp.R

@Composable
fun AboutScreen(
    onBackPress: () -> Unit,
    onAuthorClick: () -> Unit
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
                        painter = painterResource(id = R.drawable.ic_launcher),
                        contentDescription = "Owner image",
                        modifier = Modifier
                            .padding(top = 64.dp)
                            .size(85.dp)
                            .clip(CircleShape)
                            .border(
                                width = 2.dp,
                                color = Color(0xFFD4BDF7),
                                shape = CircleShape
                            ),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = "Dictionary of the Tourism terms in English, Uzbek and Russian languages",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(510),
                            color = Color(0xFFF1EAFC),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier.padding(
                            top = 10.dp,
                            start = 16.dp,
                            end = 16.dp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 3.dp)
                        .fillMaxWidth()
                        .background(Color.White)
                        .clickable(
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = rememberRipple()
                        ) {
                            onAuthorClick()
                        }
                        .padding(
                            horizontal = 16.dp,
                            vertical = 10.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Muallif haqida",
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 22.sp,
                            fontWeight = FontWeight(410),
                            color = Color(0xFF0A0D10),
                            letterSpacing = 0.27.sp,
                        )
                    )

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Next",
                        tint = Color(0xFF0A0D10)
                    )

                }

                Text(
                    text = "INFORMATION ABOUT THE DICTIONARY",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF0A0D10),
                        letterSpacing = 0.24.sp,
                    ),
                    modifier = Modifier
                        .shadow(
                            elevation = 2.dp,
                            spotColor = Color(0x261C1F27),
                            ambientColor = Color(0x261C1F27)
                        )
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFFFFF))
                        .padding(
                            start = 16.dp,
                            top = 10.dp,
                            end = 16.dp
                        ),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "This explanatory dictionary of terms related to tourism in English-Uzbek-Russian languages is suitable for students of lyceums, colleges, educational institutions and universities, teachers and translators, as well as specialists who have set themselves the goal of studying the field of tourism. intended.\n" +
                            "\n" +
                            "The main task of the dictionary is to help learners of terms related to tourism and introduce them to the modern lexical wealth of this field.\n" +
                            "\n" +
                            "In the dictionary, firstly, the terms related to tourism in English are given, then the terms in Russian and Uzbek languages are given, and the units in all three languages are given in alphabetical order and their explanations are given. This electronic dictionary was prepared as part of the scientific research on \"Current issues of translation and linguistics\", and it is dedicated to the explanation of terms related to tourism in English, Russian and Uzbek languages. This dictionary is important as the first work of Uzbek lexicography classified within the framework of English, Russian and Uzbek languages in the system of terms related to tourism.\n" +
                            "\n" +
                            "The dictionary contains more than 3,000 words according to its function and structure. About 2,000 of them are phrases. The dictionary was compiled based on the scientific conclusions of linguists and lexicographical scientists and based on existing lexicographical principles.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF0A0D10),
                        letterSpacing = 0.24.sp,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(
                            horizontal = 16.dp,
                            vertical = 10.dp
                        )
                )

                Text(
                    text = "LUG’AT HAQIDA MA’LUMOT",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF0A0D10),
                        letterSpacing = 0.24.sp,
                    ),
                    modifier = Modifier
                        .shadow(
                            elevation = 2.dp,
                            spotColor = Color(0x261C1F27),
                            ambientColor = Color(0x261C1F27)
                        )
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFFFFF))
                        .padding(
                            start = 16.dp,
                            top = 10.dp,
                            end = 16.dp
                        ),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Ushbu turizmga oid terminlarning izohli lug‘atining inglizcha-о‘zbekcha-ruscha tillarida о‘z oldiga turizm sohasini о‘rganishni maqsad qilib qо‘ygan litsey, kolledj, о‘quv yurtlari talabalari, о‘qituvchi va tarjimonlar hamda mutaxassislar uchun mо‘ljallangan.\n" +
                            "\n" +
                            "Lug‘atning asosiy vazifasi turizmga oid terminlarni о‘rganuvchilarga yordam berish va shu sohaning zamonaviy leksik boyligi bilan tanishtirishdir.\n" +
                            "\n" +
                            "Lug’atda dastlab ingliz tilidagi turizmga oid terminlar undan keyin rus va o’zbek tillardagi terminlar keltirilib, har uchala tildagi birliklar ham alifbo tarkibida berilib ularning izohlari keltirilgan. Mazkur electron lug’at “Tarjimashunoslik va tilshunoslikning dolzarb masalalari” mavzusidagi ilmiy tadqiqot doirasida tayyorlangan bo’lib, u ingliz, rus va o’zbek tillaridagi turizmga oid terminlarning izohiga bag’ishlangan. Ushbu lug’at o’zbek lug’atchiligining turizmga oid terminlar tizimida ingliz, rus va o’zbek tillari doirasida tasniflangan dastlabki asar sifatida ahamiyatlidir.\n" +
                            "\n" +
                            "Lug‘at о‘z vazifasiga va tuzilishiga  kо‘ra  3 000 dan ortiq sо‘zni о‘z ichiga olgan. Ulardan 2 000ga yaqini iboralardan iborat. Lug’at tilshunos, leksikografik olimlarning ilmiy xulosalariga tayangan holda hamda mavjud leksikografik tamoyillar asosida tuzilgan.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF0A0D10),
                        letterSpacing = 0.24.sp,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(
                            horizontal = 16.dp,
                            vertical = 10.dp
                        )
                )

                Text(
                    text = "ИНФОРМАЦИЯ О СЛОВАРЕ",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF0A0D10),
                        letterSpacing = 0.24.sp,
                    ),
                    modifier = Modifier
                        .shadow(
                            elevation = 2.dp,
                            spotColor = Color(0x261C1F27),
                            ambientColor = Color(0x261C1F27)
                        )
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFFFFF))
                        .padding(
                            start = 16.dp,
                            top = 10.dp,
                            end = 16.dp
                        ),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Данный толковый словарь терминов, связанных с туризмом, на английском-узбекско-русском языках подойдет учащимся лицеев, колледжей, учебных заведений, преподавателям и переводчикам, а также специалистам, поставившим перед собой цель изучения сферы туризма. намеревался.\n" +
                            "\n" +
                            "Основная задача словаря – помочь изучающим термины, связанные с туризмом, и познакомить их с современным лексическим богатством этой области.\n" +
                            "\n" +
                            "В словаре сначала приводятся термины, связанные с туризмом, на английском языке, затем - термины на русском и узбекском языках, а также единицы на всех трех языках в алфавитном порядке и даны их пояснения. Данный электронный словарь подготовлен в рамках научного исследования «Актуальные вопросы перевода и лингвистики» и посвящен разъяснению терминов, связанных с туризмом, на английском, русском и узбекском языках. Этот словарь важен как первый труд узбекской лексикографии, классифицированный в рамках английского, русского и узбекского языков в системе терминов, связанных с туризмом.\n" +
                            "\n" +
                            "Словарь содержит более 3000 слов по своему назначению и структуре. Около 2000 из них — фразы. Словарь составлен на основе научных выводов лингвистов и учёных-лексикографов и на основе существующих лексикографических принципов.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF0A0D10),
                        letterSpacing = 0.24.sp,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(
                            horizontal = 16.dp,
                            vertical = 10.dp
                        )
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