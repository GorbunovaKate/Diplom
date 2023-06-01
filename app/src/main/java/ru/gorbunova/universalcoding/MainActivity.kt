package ru.gorbunova.universalcoding

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.gorbunova.universalcoding.presentation.navigation.AppNavHost
import ru.gorbunova.universalcoding.ui.theme.UniversalCodingTheme

class MainActivity : ComponentActivity() {


    private val _viewModel: MainViewModel by viewModels()
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                _viewModel.isLoading.value
            }
        }

        setContent {
            val prefs = PreferenceManager.getDefaultSharedPreferences(LocalContext.current)
            var sysdarkTheme: Boolean = isSystemInDarkTheme()
            var darkTheme by remember { mutableStateOf(prefs.getBoolean("dark_theme", sysdarkTheme)) }
            UniversalCodingTheme(darkTheme = darkTheme) {

                val context = LocalContext.current
                val mViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
                mViewModel.initDatabase()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(mViewModel,
                        darkTheme = darkTheme,
                        onThemeUpdated = { darkTheme = !darkTheme
                            prefs.edit().putBoolean("dark_theme", darkTheme).apply()}
                    )
                }
            }
        }
    }
}
