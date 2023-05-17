package ru.gorbunova.universalcoding

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.gorbunova.universalcoding.presentation.navigation.AppNavHost
import ru.gorbunova.universalcoding.ui.theme.UniversalCodingTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniversalCodingTheme {

                val context = LocalContext.current
                val mViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
                mViewModel.initDatabase()

                // A surface container using the 'background' color from the theme
                androidx.compose.material.Scaffold(
                    content = {
                        androidx.compose.material.Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = androidx.compose.material.MaterialTheme.colors.background
                        ) {
                            AppNavHost(mViewModel)
                        }
                    }
                )
            }
        }
    }
}
