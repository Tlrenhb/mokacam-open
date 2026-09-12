package com.aee.mokacam.activity

import android.os.Bundle
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
import com.aee.mokacam.bean.SendMsg
import com.aee.mokacam.constants.AeeConstants
import com.aee.mokacam.service.n

@OptIn(ExperimentalMaterial3Api::class)
class ComposeWifiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            androidx.compose.material3.MaterialTheme {
                var ssid by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var status by remember { mutableStateOf("") }
                Scaffold(topBar = { TopAppBar(title = { Text("相机 Wi‑Fi") }) }) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding).padding(20.dp)) {
                        OutlinedTextField(ssid, { ssid = it }, Modifier.fillMaxWidth(), label = { Text("SSID") })
                        OutlinedTextField(password, { password = it }, Modifier.fillMaxWidth(), label = { Text("密码") })
                        Button(onClick = {
                            if (password.length < 8) status = "密码至少 8 位"
                            else {
                                com.aee.mokacam.service.a.a().a(object : n { override fun a(value: Any?) {} }, SendMsg(2049, "$ssid\$$password", null))
                                status = "Wi‑Fi 配置已发送，设备将重启"
                            }
                        }, Modifier.fillMaxWidth().padding(top = 16.dp)) { Text("保存并重启 Wi‑Fi") }
                        Text(status, Modifier.padding(top = 12.dp))
                    }
                }
            }
        }
    }
}
