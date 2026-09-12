package com.aee.mokacam.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
class ComposeInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val section = intent.getStringExtra("section") ?: "support"
        setContent {
            MaterialTheme {
                var tab by remember { mutableIntStateOf(0) }
                val title = when (section) {
                    "product" -> "产品与参数"
                    "legal" -> "法律信息"
                    "register" -> "产品注册"
                    else -> "支持中心"
                }
                Scaffold(topBar = { TopAppBar(title = { Text(title) }) }) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding)) {
                        if (section == "support") {
                            TabRow(tab) {
                                Tab(tab == 0, { tab = 0 }, text = { Text("相机") })
                                Tab(tab == 1, { tab = 1 }, text = { Text("无人机") })
                            }
                        }
                        val rows = when (section) {
                            "product" -> listOf("Mokacam", "1080P 60fps 视频", "1200 万像素照片", "Wi‑Fi 实时预览", "轻量便携机身")
                            "legal" -> listOf("请遵守当地无人机与相机使用法律法规。", "避免在机场、禁飞区和人群上方操作。", "请在使用前阅读设备安全说明。")
                            "register" -> listOf("产品注册", "设备序列号", "用户信息", "提交注册信息")
                            else -> if (tab == 0) listOf("无法连接 Wi‑Fi？检查相机 AP 与手机网络。", "画面模糊？清洁镜头并恢复相机设置。", "SD 卡异常？备份后在相机设置中格式化。") else listOf("无人机不能起飞？检查桨叶、电池和校准。", "悬停偏移？清洁光流镜片并重新校准。", "遥控器连接失败？重启飞行器和遥控器。")
                        }
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            items(rows) { row ->
                                ListItem(headlineContent = { Text(row) }, supportingContent = {
                                    if (section == "product") Text("AEE Mokacam")
                                    else Text("点击原功能页继续操作")
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}
