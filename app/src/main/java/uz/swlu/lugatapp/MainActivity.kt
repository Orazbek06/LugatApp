package uz.swlu.lugatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import org.apache.poi.ss.usermodel.WorkbookFactory
import uz.swlu.lugatapp.navigation.MainNavigation
import uz.swlu.lugatapp.navigation.Screen
import uz.swlu.lugatapp.navigation.rememberNavigationState
import uz.swlu.lugatapp.pref.MyPref
import uz.swlu.lugatapp.ui.screen.AboutScreen
import uz.swlu.lugatapp.ui.screen.HomeScreen
import uz.swlu.lugatapp.ui.screen.LetterScreen
import uz.swlu.lugatapp.ui.theme.LugatAppTheme
import uz.swlu.lugatapp.ui.viewmodel.MainViewModel
import uz.swlu.lugatapp.ui.viewmodel.WordsViewModelImpl
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var pref: MyPref

    private val viewModel: MainViewModel by viewModels<WordsViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            LugatAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    val navigationState = rememberNavigationState()

                    MainNavigation(
                        navController = navigationState.navHostController,
                        homeScreen = {
                            HomeScreen(
                                viewModel = viewModel,
                                onLetterClick = {
                                    viewModel.getLetterWords(
                                        "${it[0]}",
                                        ""
                                    )
                                    navigationState.navigateTo(
                                        Screen.LetterContent.getRouteWithArgs(
                                            it
                                        )
                                    )
                                },
                                onAboutClick = {
                                    navigationState.navigateTo(
                                        Screen.AboutContent.route
                                    )
                                }
                            )
                        },
                        letterScreen = { letter ->
                            LetterScreen(
                                letter,
                                viewModel = viewModel,
                                onBackPress = {
                                    navigationState.navHostController.popBackStack()
                                }
                            )
                        },
                        aboutScreen = {
                            AboutScreen(
                                onBackPress = {
                                    navigationState.navHostController.popBackStack()
                                }
                            )
                        }
                    )
                }
            }
        }

        window.statusBarColor = getColor(R.color.statusBarColor)

        if (!pref.startScreen) {
            val file = this.resources.openRawResource(R.raw.words)
            val excel = WorkbookFactory.create(file)
            viewModel.insertWords(excel)
        } else {
            viewModel.getWords("")
            viewModel.getLetters()
        }

    }
}
