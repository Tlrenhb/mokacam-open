package com.aee.mokacam.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
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
import com.aee.mokacam.AeeApplication
import com.aee.mokacam.constants.AeeConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

class ComposeDownloadActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val names = intent.getStringArrayListExtra("files") ?: arrayListOf()
        setContent {
            androidx.compose.material3.MaterialTheme {
                var progress by remember { mutableIntStateOf(0) }
                var status by remember { mutableStateOf("等待下载") }
                Scaffold(topBar = { TopAppBar(title = { Text("下载") }) }) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding).padding(20.dp)) {
                        Text("任务数：${names.size}")
                        LinearProgressIndicator(progress = { progress / 100f }, Modifier.fillMaxWidth().padding(vertical = 16.dp))
                        Button(onClick = {
                            status = "正在下载…"
                            CoroutineScope(Dispatchers.IO).launch {
                                var done = 0
                                names.forEach { name ->
                                    download(name) { percent ->
                                        runOnUiThread { progress = ((done * 100) + percent) / names.size.coerceAtLeast(1) }
                                    }
                                    done++
                                }
                                runOnUiThread { progress = 100; status = "下载完成" }
                            }
                        }, Modifier.fillMaxWidth()) { Text("开始下载") }
                        Text(status, Modifier.padding(top = 14.dp))
                    }
                }
            }
        }
    }

    private fun download(name: String, onProgress: (Int) -> Unit) {
        val base = AeeApplication.a()?.h ?: AeeConstants.h
        val target = File(AeeConstants.a, name)
        target.parentFile?.mkdirs()
        val part = File(target.absolutePath + ".part")
        val connection = URL(base + name).openConnection() as HttpURLConnection
        connection.connectTimeout = 8000
        connection.readTimeout = 20000
        connection.inputStream.use { input ->
            part.outputStream().use { output ->
                val buffer = ByteArray(16 * 1024)
                val total = connection.contentLengthLong
                var readTotal = 0L
                var count: Int
                while (input.read(buffer).also { count = it } > 0) {
                    output.write(buffer, 0, count)
                    readTotal += count
                    if (total > 0) onProgress((readTotal * 100 / total).toInt())
                }
            }
        }
        connection.disconnect()
        if (!part.renameTo(target)) throw IllegalStateException("download rename failed")
    }
}
