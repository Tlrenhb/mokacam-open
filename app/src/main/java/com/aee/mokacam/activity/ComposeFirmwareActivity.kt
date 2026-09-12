package com.aee.mokacam.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aee.mokacam.constants.AeeConstants
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
class ComposeFirmwareActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var progress by remember { mutableIntStateOf(0) }
                var status by remember { mutableStateOf("等待升级文件") }
                val firmware = File(AeeConstants.f, "firmware.bin")
                Scaffold(topBar = { TopAppBar(title = { Text("固件升级") }) }) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding).padding(20.dp)) {
                        Text("文件：${firmware.absolutePath}")
                        Text(if (firmware.exists()) "已找到固件文件" else "未找到 firmware.bin")
                        LinearProgressIndicator(progress = { progress / 100f }, Modifier.fillMaxWidth().padding(vertical = 16.dp))
                        Button(onClick = {
                            if (!firmware.exists()) {
                                status = "请将 firmware.bin 放入 Mokacam/fwupdate/"
                            } else {
                                status = "正在上传…"
                                Thread {
                                    com.aee.mokacam.service.a.a().a(Handler(Looper.getMainLooper()) { msg ->
                                        if (msg.what == 32800) {
                                            progress = 100
                                            status = "升级文件已发送，等待相机重启"
                                        }
                                        true
                                    })
                                }.start()
                            }
                        }, Modifier.fillMaxWidth()) { Text("开始升级") }
                        Text(status, Modifier.padding(top = 16.dp))
                    }
                }
            }
        }
    }
}
