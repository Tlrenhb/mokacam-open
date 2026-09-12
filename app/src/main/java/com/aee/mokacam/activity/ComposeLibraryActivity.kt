package com.aee.mokacam.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.aee.mokacam.AeeApplication
import com.aee.mokacam.bean.SendMsg
import com.aee.mokacam.bean.g
import com.aee.mokacam.constants.AeeConstants
import com.aee.mokacam.service.n
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
class ComposeLibraryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val context = LocalContext.current
                var loading by remember { mutableStateOf(true) }
                var selected by remember { mutableStateOf<Set<String>>(emptySet()) }
                var status by remember { mutableStateOf("") }
                val files = remember { mutableStateListOf<g>() }
                fun path(file: g) = "/tmp/SD0/moka/${file.b()}${file.a()}"
                fun refresh() {
                    loading = true
                    com.aee.mokacam.service.a.a().a(object : n {
                        override fun a(value: Any?) {
                            val result = (value as? com.aee.mokacam.bean.ReceiveMsg)?.getListing()
                            files.clear()
                            files.addAll(g.a(result ?: "[]"))
                            selected = emptySet()
                            loading = false
                        }
                    }, SendMsg(AeeConstants.l, "/tmp/SD0/moka/", null))
                }
                LaunchedEffect(Unit) { withContext(Dispatchers.IO) { refresh() } }
                Scaffold(topBar = { TopAppBar(title = { Text("相册") }) }) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
                        if (loading) Text("正在读取相机文件…")
                        else if (files.isEmpty()) Text("没有媒体文件")
                        if (selected.isNotEmpty()) {
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(onClick = {
                                    selected.mapNotNull { name -> files.find { it.a() == name } }.forEach { file ->
                                        com.aee.mokacam.service.a.a().a(object : n { override fun a(value: Any?) {} }, SendMsg(AeeConstants.k, path(file), null))
                                    }
                                    status = "删除请求已发送"
                                    refresh()
                                }) { Text("删除") }
                                Button(onClick = {
                                    startActivity(Intent(context, DownLoadActivity::class.java))
                                }) { Text("下载") }
                            }
                        }
                        if (status.isNotEmpty()) Text(status)
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(files, key = { it.a() }) { file ->
                                val checked = file.a() in selected
                                Card(Modifier.fillMaxWidth()) {
                                    Row(Modifier.fillMaxWidth().padding(14.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                        Column(Modifier.weight(1f)) {
                                            Text(file.a(), style = MaterialTheme.typography.titleMedium)
                                            Text(file.b() ?: "", style = MaterialTheme.typography.bodySmall)
                                        }
                                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                            Button(onClick = {
                                                selected = if (checked) selected - file.a() else selected + file.a()
                                            }) { Text(if (checked) "已选" else "选择") }
                                            Button(onClick = {
                                                startActivity(Intent(this@ComposeLibraryActivity, ComposeMediaActivity::class.java).apply {
                                                    putExtra("filePath", file.a())
                                                })
                                            }) { Text(if (file.a().endsWith(".MP4", true)) "播放" else "查看") }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
