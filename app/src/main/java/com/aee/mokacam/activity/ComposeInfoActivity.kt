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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
class ComposeInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val section = intent.getStringExtra("section") ?: "support"
        setContent {
            MaterialTheme {
                val title = when (section) {
                    "product" -> "产品与参数"
                    "legal" -> "法律信息"
                    "register" -> "产品注册"
                    else -> "支持中心"
                }
                Scaffold(topBar = { TopAppBar(title = { Text(title) }) }) { padding ->
                    Column(Modifier.fillMaxSize().padding(padding)) {
                        val rows = when (section) {
                            "product" -> listOf("Mokacam", "1080P 60fps 视频", "1200 万像素照片", "Wi‑Fi 实时预览", "轻量便携机身")
                            "legal" -> listOf("请遵守相机拍摄、隐私与当地使用法律法规。", "请在使用前阅读设备安全说明。", "请勿在危险环境中使用设备。")
                            "register" -> listOf("产品注册", "设备序列号", "用户信息", "提交注册信息")
                            else -> listOf("无法连接 Wi‑Fi？检查相机 AP 与手机网络。", "画面模糊？清洁镜头并恢复相机设置。", "SD 卡异常？备份后在相机设置中格式化。")
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
