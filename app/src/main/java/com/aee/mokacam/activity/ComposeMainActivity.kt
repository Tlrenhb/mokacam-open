package com.aee.mokacam.activity

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aee.mokacam.AeeApplication
import com.aee.mokacam.R
import com.aee.mokacam.constants.AeeConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MokacamTheme { MokacamAppScreen() } }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun MokacamAppScreen() {
        val context = LocalContext.current
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var connecting by remember { mutableStateOf(false) }
        var connected by remember { mutableStateOf(AeeApplication.a()?.f == true) }
        var message by remember { mutableStateOf("") }

        fun navigate(clazz: Class<*>) {
            context.startActivity(Intent(context, clazz))
            scope.launch { drawerState.close() }
        }

        fun openInfo(section: String) {
            context.startActivity(Intent(context, ComposeInfoActivity::class.java).putExtra("section", section))
            scope.launch { drawerState.close() }
        }

        fun connect() {
            val wifi = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager
            if (wifi == null || !wifi.isWifiEnabled) {
                message = context.getString(R.string.please_open_wifi)
                return
            }
            val ip = wifi.connectionInfo?.ipAddress ?: 0
            val dotted = "${ip and 255}.${ip shr 8 and 255}.${ip shr 16 and 255}.${ip shr 24 and 255}"
            if (!dotted.startsWith("192.168.42")) {
                message = context.getString(R.string.pleaseconnect)
                return
            }
            connecting = true
            message = context.getString(R.string.connecting)
            scope.launch {
                withContext(Dispatchers.IO) { com.aee.mokacam.service.a.a() }
                delay(2500)
                connected = AeeApplication.a()?.f == true || com.aee.mokacam.service.a.a != null
                AeeApplication.a()?.f = connected
                connecting = false
                message = if (connected) context.getString(R.string.isconnected) else context.getString(R.string.connect_error)
            }
        }

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = Color(0xFF303033),
                    modifier = Modifier.width(280.dp)
                ) {
                    Text("Mokacam", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(24.dp))
                    HorizontalDivider(color = Color.White.copy(alpha = .25f))
                    DrawerItem("产品参数") { openInfo("product") }
                    DrawerItem("设置") { navigate(ComposeSettingsActivity::class.java) }
                    DrawerItem("无人机控制") { navigate(ComposeDroneActivity::class.java) }
                    DrawerItem("支持") { openInfo("support") }
                    DrawerItem("法律信息") { openInfo("legal") }
                    DrawerItem("官网") {
                        context.startActivity(Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://www.aee.com")))
                        scope.launch { drawerState.close() }
                    }
                }
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("AEE Mokacam", fontWeight = FontWeight.Bold) },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "菜单")
                            }
                        }
                    )
                }
            ) { padding ->
                Column(
                    modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(30.dp))
                    Text("RECORD YOUR LIMITS", color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(24.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth().height(190.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("MOKACAM", fontSize = 30.sp, fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary)
                    }
                    Spacer(Modifier.height(28.dp))
                    Button(
                        onClick = { connect() },
                        enabled = !connecting,
                        modifier = Modifier.fillMaxWidth().height(54.dp),
                        shape = RoundedCornerShape(28.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (connected) Color(0xFF2E9D61) else MaterialTheme.colorScheme.primary
                        )
                    ) { Text(if (connecting) "正在连接…" else if (connected) "已连接相机" else "连接相机", fontSize = 16.sp) }
                    Spacer(Modifier.height(8.dp))
                    if (message.isNotEmpty()) Text(message, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(20.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                        Button(onClick = { navigate(ComposeLibraryActivity::class.java) }, modifier = Modifier.weight(1f)) { Text("相册") }
                        Button(onClick = { if (connected) navigate(ComposeCameraActivity::class.java) else connect() }, modifier = Modifier.weight(1f)) { Text("实时预览") }
                    }
                }
            }
        }
    }

    @Composable
    private fun DrawerItem(label: String, onClick: () -> Unit) {
        NavigationDrawerItem(
            label = { Text(label, color = Color.White) },
            selected = false,
            onClick = onClick,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}

@Composable
private fun MokacamTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF006A6A),
            secondary = Color(0xFF4D6363),
            tertiary = Color(0xFF4F5F7A)
        ),
        content = content
    )
}
