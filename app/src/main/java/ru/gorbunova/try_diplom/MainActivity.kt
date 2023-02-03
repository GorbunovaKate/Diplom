package ru.gorbunova.try_diplom

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TopAppBar
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ru.gorbunova.try_diplom.components.BottomNavBar
import ru.gorbunova.try_diplom.navigation.AppNavHost
import ru.gorbunova.try_diplom.ui.theme.Try_DiplomTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            Try_DiplomTheme {

                val context = LocalContext.current
                val mViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
                mViewModel.initDatabase()


                androidx.compose.material.Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                androidx.compose.material.Text(text = "Универсальное кодирование")
                            },
                            backgroundColor = Color.Blue,
                            contentColor = Color.White,
                            elevation = 12.dp
                        )
                    },
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
