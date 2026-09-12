package com.aee.mokacam.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
class ComposeSettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var welcome by remember { mutableStateOf(true) }
                Scaffold(topBar = { TopAppBar(title = { Text("设置") }) }) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding), verticalArrangement = Arrangement.Top) {
                        ListItem(headlineContent = { Text("相机设置") }, supportingContent = { Text("录像、拍照、提示音、时间、SD 卡、Wi‑Fi") },
                            modifier = Modifier.fillMaxWidth().clickable { startActivity(Intent(this@ComposeSettingsActivity, ComposeCameraSettingsActivity::class.java)) }, trailingContent = { Text("›") },
                            leadingContent = { Text("⚙") },
                            tonalElevation = 1.dp)
                        HorizontalDivider()
                        ListItem(headlineContent = { Text("进入相机设置") }, supportingContent = { Text("打开完整相机参数页面") },
                            modifier = Modifier.fillMaxWidth(), leadingContent = { Text("📷") },
                            trailingContent = { Text("›") })
                        HorizontalDivider()
                        ListItem(headlineContent = { Text("Wi‑Fi 密码") }, supportingContent = { Text("修改相机 SSID 与密码") },
                            modifier = Modifier.fillMaxWidth().clickable { startActivity(Intent(this@ComposeSettingsActivity, ComposeWifiActivity::class.java)) }, leadingContent = { Text("▣") }, trailingContent = { Text("›") })
                        HorizontalDivider()
                        ListItem(headlineContent = { Text("欢迎页") }, supportingContent = { Text("启动时显示欢迎页") },
                            modifier = Modifier.fillMaxWidth(), trailingContent = { Switch(checked = welcome, onCheckedChange = { welcome = it }) })
                        HorizontalDivider()
                        ListItem(headlineContent = { Text("固件升级") }, supportingContent = { Text("从 Mokacam/fwupdate/firmware.bin 上传") },
                            modifier = Modifier.fillMaxWidth().clickable { startActivity(Intent(this@ComposeSettingsActivity, ComposeFirmwareActivity::class.java)) }, leadingContent = { Text("↑") }, trailingContent = { Text("›") })
                        HorizontalDivider()
                        ListItem(headlineContent = { Text("清除缓存") }, supportingContent = { Text("应用设置与缓存") },
                            modifier = Modifier.fillMaxWidth(), trailingContent = { Text("清除") })
                    }
                }
            }
        }
        // Compose rows remain fully visible; launch the legacy protocol-backed
        // screens from explicit click wiring in the next migration slice.
    }
}
