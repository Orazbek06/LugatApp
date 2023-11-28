package uz.swlu.lugatapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.AndroidEntryPoint
import org.apache.poi.ss.usermodel.WorkbookFactory
import uz.swlu.lugatapp.navigation.MainNavigation
import uz.swlu.lugatapp.navigation.Screen
import uz.swlu.lugatapp.navigation.rememberNavigationState
import uz.swlu.lugatapp.pref.MyPref
import uz.swlu.lugatapp.ui.screen.AboutOwnerScreen
import uz.swlu.lugatapp.ui.screen.AboutScreen
import uz.swlu.lugatapp.ui.screen.HomeScreen
import uz.swlu.lugatapp.ui.screen.LetterScreen
import uz.swlu.lugatapp.ui.theme.LugatAppTheme
import uz.swlu.lugatapp.ui.viewmodel.MainViewModel
import uz.swlu.lugatapp.ui.viewmodel.WordsViewModelImpl
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    companion object {
        const val APP_UPDATE_REQUEST_CODE = 100
    }

    @Inject
    lateinit var pref: MyPref

    private lateinit var appUpdateManager: AppUpdateManager
    private var updateInfo: AppUpdateInfo? = null
    private var updateListener = InstallStateUpdatedListener { state: InstallState ->
        if (state.installStatus() == InstallStatus.DOWNLOADED) {
            appUpdateManager.completeUpdate()
        }
    }

    private var installStateUpdatedListener: InstallStateUpdatedListener =
        object : InstallStateUpdatedListener {
            override fun onStateUpdate(state: InstallState) {
                if (state.installStatus() == InstallStatus.DOWNLOADED) {
                    appUpdateManager.completeUpdate()
                } else if (state.installStatus() == InstallStatus.INSTALLED) {
                    appUpdateManager.unregisterListener(this)
                }
            }
        }

    private fun startForInAppUpdate(it: AppUpdateInfo?) {
        appUpdateManager.startUpdateFlowForResult(
            it!!,
            AppUpdateType.IMMEDIATE,
            this,
            APP_UPDATE_REQUEST_CODE
        )
    }

    private fun checkForUpdate() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                it.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                updateInfo = it
                startForInAppUpdate(updateInfo)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        appUpdateManager.unregisterListener(installStateUpdatedListener)
    }

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
                                },
                                onAuthorClick = {
                                    navigationState.navigateTo(
                                        Screen.AboutOwnerContent.route
                                    )
                                }
                            )
                        },
                        aboutOwnerScreen = {
                            AboutOwnerScreen(
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

        val versionCode = BuildConfig.VERSION_CODE

        if (!pref.startScreen || pref.lastVersion < versionCode) {
            val file = this.resources.openRawResource(R.raw.words)
            val excel = WorkbookFactory.create(file)
            viewModel.insertWords(excel)
            pref.lastVersion = versionCode
        } else {
            viewModel.getWords("")
            viewModel.getLetters()
        }

        try {
            appUpdateManager = AppUpdateManagerFactory.create(this)
            appUpdateManager.registerListener(updateListener)
            checkForUpdate()
        } catch (_: Exception) {

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == APP_UPDATE_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {

                }

                Activity.RESULT_CANCELED -> {
                }

                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> {
                }
            }
        }
    }
}
