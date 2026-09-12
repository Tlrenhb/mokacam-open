package com.aee.mokacam.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aee.mokacam.service.v

@OptIn(ExperimentalMaterial3Api::class)
class ComposeDroneActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            androidx.compose.material3.MaterialTheme {
                var connected by remember { mutableStateOf(false) }
                var throttle by remember { mutableFloatStateOf(.5f) }
                var roll by remember { mutableFloatStateOf(.5f) }
                var pitch by remember { mutableFloatStateOf(.5f) }
                var yaw by remember { mutableFloatStateOf(.5f) }
                fun sendSticks() {
                    val flight = v.b ?: return
                    val value = { x: Float -> (1000 + (x * 1000f)).toInt() }
                    flight.h = value(throttle)
                    flight.i = value(roll)
                    flight.j = value(pitch)
                    flight.k = value(yaw)
                    flight.b()
                }
                Scaffold(topBar = { TopAppBar(title = { Text("无人机控制") }) }) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding).padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(if (connected) "飞控已连接" else "飞控未连接")
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(onClick = { v.a(); connected = true }, modifier = Modifier.weight(1f)) { Text("连接") }
                            OutlinedButton(onClick = { v.a().b(); connected = false }, modifier = Modifier.weight(1f)) { Text("断开") }
                        }
                        Text("油门 ${percent(throttle)}%")
                        Slider(throttle, { throttle = it }, modifier = Modifier.fillMaxWidth())
                        Text("横滚 ${percent(roll)}%")
                        Slider(roll, { roll = it }, modifier = Modifier.fillMaxWidth())
                        Text("俯仰 ${percent(pitch)}%")
                        Slider(pitch, { pitch = it }, modifier = Modifier.fillMaxWidth())
                        Text("偏航 ${percent(yaw)}%")
                        Slider(yaw, { yaw = it }, modifier = Modifier.fillMaxWidth())
                        Button(onClick = { sendSticks() }, modifier = Modifier.fillMaxWidth()) { Text("发送摇杆状态") }
                        Text("起飞/降落等飞控动作保留原协议入口，请在真实设备上谨慎操作。")
                    }
                }
            }
        }
    }

    private fun percent(value: Float): Int = (value * 100).toInt()
}
