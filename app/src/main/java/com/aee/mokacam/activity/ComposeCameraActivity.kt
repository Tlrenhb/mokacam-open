package com.aee.mokacam.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.aee.mokacam.R
import com.android.gl2jni.a

class ComposeCameraActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CameraScreen() }
    }

    @Composable
    private fun CameraScreen() {
        val context = LocalContext.current
        val owner = LocalLifecycleOwner.current
        var recording by remember { mutableStateOf(false) }
        var mode by remember { mutableStateOf("录像") }
        var status by remember { mutableStateOf("正在加载预览") }
        val preview = remember { a(context, this) }
        val handler = remember {
            Handler(Looper.getMainLooper()) { msg ->
                if (msg.what == 32773) {
                    status = if (msg.arg1 == 0) "操作成功" else "操作失败：${msg.arg1}"
                    if (recording) recording = false
                }
                true
            }
        }

        DisposableEffect(owner, preview) {
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_RESUME -> preview.onResume()
                    Lifecycle.Event.ON_PAUSE -> preview.onPause()
                    Lifecycle.Event.ON_DESTROY -> preview.release()
                    else -> Unit
                }
            }
            owner.lifecycle.addObserver(observer)
            onDispose {
                owner.lifecycle.removeObserver(observer)
                preview.release()
            }
        }

        Scaffold(containerColor = Color.Black) { padding ->
            Box(Modifier.fillMaxSize().padding(padding)) {
                AndroidView(factory = { preview }, modifier = Modifier.fillMaxSize())
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        Modifier.fillMaxWidth().background(Color.Black.copy(alpha = .65f)).padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(onClick = { finish() }) { Text("返回") }
                        Spacer(Modifier.weight(1f))
                        Text(status, color = Color.White)
                    }
                    Column(Modifier.fillMaxWidth().background(Color.Black.copy(alpha = .72f)).padding(12.dp)) {
                        Text("模式：$mode", color = Color.White, style = MaterialTheme.typography.labelLarge)
                        Spacer(Modifier.height(8.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            FilledTonalButton(onClick = { mode = "录像" }, modifier = Modifier.weight(1f)) { Text("录像模式") }
                            FilledTonalButton(onClick = { mode = "拍照" }, modifier = Modifier.weight(1f)) { Text("拍照模式") }
                        }
                        Spacer(Modifier.height(8.dp))
                        Button(
                            onClick = {
                                if (mode == "录像") {
                                    recording = !recording
                                    com.aee.mokacam.service.a.a().a(recording, handler, true)
                                    status = if (recording) "正在录像" else "停止录像"
                                } else {
                                    com.aee.mokacam.service.a.a().a(handler, 0, true)
                                    status = "正在拍照"
                                }
                            },
                            modifier = Modifier.fillMaxWidth().height(52.dp)
                        ) { Text(if (mode == "录像") if (recording) "停止录像" else "开始录像" else "拍照") }
                    }
                }
            }
        }
    }
}
