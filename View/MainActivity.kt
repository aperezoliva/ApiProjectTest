package com.example.apiproject.View

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.apiproject.Controller.UserControls
import com.example.apiproject.ui.theme.ApiProjectTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
//                    val nameParameter = "name:"
//                    sendRequest("JSON",nameParameter + "Resident Evil 4")
                    GameData()
                    val navController = rememberNavController()
//                    NavHost(navController = navController,
//                            startDestination = "main_screen")
//                    {
//                        composable("main_screen") {
//
//                        }
//                    }
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun GameData() {
        val requestControl = UserControls()
        val ctx = LocalContext.current
        val gameName = remember {
            mutableStateOf(TextFieldValue())
        }
        val response = remember {
            mutableListOf(mutableListOf("test1", "test2"))
        }

        val selectedOption = remember {
            mutableStateOf("")
        }
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TextField(
                    value = gameName.value,
                    onValueChange = { gameName.value = it },
                    label = { Text("test") },
                    singleLine = true,
                    trailingIcon = {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Clear Field",
                            modifier = Modifier.clickable {
                                gameName.value = TextFieldValue("")
                            }
                        )
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        GlobalScope.launch {
                            suspend {
                                requestControl.sendRequest(ctx, gameName, response)
                            }.invoke()
                        }


                        Log.d("onClick", "radioOptions value: $response")

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(text = "Post data", modifier = Modifier.padding(8.dp))

                }

                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                response.forEach { options ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .selectable(
                                selected = (options.first() == selectedOption.value),
                                onClick = {
                                    selectedOption.value = options.first()
                                }
                            )
                    ) {
                        RadioButton(
                            selected = (options.first() == selectedOption.value),
                            onClick = {
                                selectedOption.value = options.first()
                            }
                        )
                        Text(
                            text = options.toString(),
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }

        }
    }
}