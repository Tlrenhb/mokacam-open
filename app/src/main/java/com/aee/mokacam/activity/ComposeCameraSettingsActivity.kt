package com.aee.mokacam.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import com.aee.mokacam.service.n

@OptIn(ExperimentalMaterial3Api::class)
class ComposeCameraSettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            androidx.compose.material3.MaterialTheme {
                var videoResolution by remember { mutableStateOf("1920x1080 60P 16:9") }
                var photoSize by remember { mutableStateOf("12M") }
                var beep by remember { mutableStateOf("on") }
                var status by remember { mutableStateOf("未修改") }
                Scaffold(topBar = { TopAppBar(title = { Text("相机设置") }) }) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("视频参数")
                        OutlinedTextField(videoResolution, { videoResolution = it }, Modifier.fillMaxWidth(), label = { Text("分辨率") })
                        Text("拍摄参数")
                        OutlinedTextField(photoSize, { photoSize = it }, Modifier.fillMaxWidth(), label = { Text("照片尺寸") })
                        OutlinedTextField(beep, { beep = it }, Modifier.fillMaxWidth(), label = { Text("提示音 on/off") })
                        Button(onClick = {
                            send(2, videoResolution, "video_resolution")
                            send(2, photoSize, "photo_size")
                            send(2, beep, "Beep")
                            status = "设置已发送"
                        }, Modifier.fillMaxWidth()) { Text("保存参数") }
                        HorizontalDivider()
                        Button(onClick = { send(8, null, null); status = "正在格式化 SD 卡" }, Modifier.fillMaxWidth()) { Text("格式化 SD 卡") }
                        Button(onClick = { send(2, "on", "default_setting"); status = "正在恢复出厂设置" }, Modifier.fillMaxWidth()) { Text("恢复出厂设置") }
                        Text(status)
                    }
                }
            }
        }
    }

    private fun send(id: Int, param: String?, type: String?) {
        com.aee.mokacam.service.a.a().a(object : n { override fun a(value: Any?) {} }, SendMsg(id, param, type))
    }
}
