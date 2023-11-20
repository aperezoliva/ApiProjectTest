package com.example.apiproject.View

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.RadioButton
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
import com.example.apiproject.Controller.ApiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsScreen(apiModel: ApiModel, modifier: Modifier = Modifier, onSubmit: () -> Unit) {
    val gameName = remember {
        mutableStateOf(TextFieldValue())
    }
    val ctx = LocalContext.current
    val response = apiModel.responseReturnValue
    val selectedOption = remember {
        mutableStateOf("")
    }
    LazyColumn(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally )
        {
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
                        apiModel.sendRequest(ctx, gameName)
                        Log.d("onClick", "radioOptions value: ${apiModel.responseReturnValue}")

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(text = "Post data", modifier = Modifier.padding(8.dp))
                }
            }
            item {
                response.forEach { options ->
                    if (options != null) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .selectable(
                                    selected = (options.first() == selectedOption.value),
                                    onClick = {
                                        if (options != null) {
                                            selectedOption.value = options.first()
                                        }
                                    }
                                )
                        ) {
                            if (options != null) {
                                RadioButton(
                                    selected = (options.first() == selectedOption.value),
                                    onClick = {
                                        selectedOption.value = options.first()
                                    }
                                )
                            }
                            Text(
                                text = options.toString(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {

                },
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Submit Games", modifier = Modifier.padding(8.dp))
                }

            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    onSubmit()
                },
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(text = "Previous screen", modifier = Modifier.padding(8.dp))
                }
            }

        }
    }
