package com.example.apiproject.View

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apiproject.Controller.ApiModel
import com.example.apiproject.ui.theme.ApiProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val modifier = Modifier.padding(50.dp)
                    val apiModel = ApiModel()
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "main_screen") {
                        composable("main_screen") {
                            DataRetrieval(apiModel, modifier) {navController.navigate("search_results")}
                        }
                        composable("search_results") {
                            ResultsScreen(apiModel, modifier) {navController.navigate("main_screen")}
                        }

                    }

                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DataRetrieval(apiModel: ApiModel, modifier: Modifier = Modifier, onSubmit: () -> Unit) {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        onSubmit()

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(text = "Search Games", modifier = Modifier.padding(8.dp))

                }

                Spacer(modifier = Modifier.height(20.dp))
            }
//            item {
//                apiModel.responseReturnValue.forEach { options ->
//                    Row(
//                        Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp)
//                            .selectable(
//                                selected = (options.first() == selectedOption.value),
//                                onClick = {
//                                    selectedOption.value = options.first()
//                                }
//                            )
//                    ) {
//                        RadioButton(
//                            selected = (options.first() == selectedOption.value),
//                            onClick = {
//                                selectedOption.value = options.first()
//                            }
//                        )
//                        Text(
//                            text = options.toString(),
//                            textAlign = TextAlign.Center
//                        )
//                    }
//                }
//
//            }

        }
    }
}