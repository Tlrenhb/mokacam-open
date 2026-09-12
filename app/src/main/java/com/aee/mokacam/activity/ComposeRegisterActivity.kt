package com.aee.mokacam.activity

import android.os.Bundle
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
class ComposeRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            androidx.compose.material3.MaterialTheme {
                val prefs = remember { getSharedPreferences("registration", Context.MODE_PRIVATE) }
                var name by remember { mutableStateOf(prefs.getString("name", "") ?: "") }
                var serial by remember { mutableStateOf(prefs.getString("serial", "") ?: "") }
                var status by remember { mutableStateOf("") }
                Scaffold(topBar = { TopAppBar(title = { Text("产品注册") }) }) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding).padding(20.dp)) {
                        OutlinedTextField(name, { name = it }, Modifier.fillMaxWidth(), label = { Text("姓名") })
                        OutlinedTextField(serial, { serial = it }, Modifier.fillMaxWidth().padding(top = 12.dp), label = { Text("产品序列号") })
                        Button(onClick = {
                            status = if (name.isBlank() || serial.isBlank()) "请填写完整信息" else {
                                prefs.edit().putString("name", name).putString("serial", serial).apply()
                                "注册信息已保存"
                            }
                        }, Modifier.fillMaxWidth().padding(top = 18.dp)) { Text("提交注册") }
                        Text(status, Modifier.padding(top = 12.dp))
                    }
                }
            }
        }
    }
}
